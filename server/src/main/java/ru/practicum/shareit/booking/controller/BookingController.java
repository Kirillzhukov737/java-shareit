package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInputDto;
import ru.practicum.shareit.booking.dto.BookingRequestParamsDto;
import ru.practicum.shareit.booking.service.BookingService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public BookingDto addBooking(@RequestBody BookingInputDto bookingInputDto,
                                 @RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long bookerId) {
        log.info("Booking {} is requested", bookingInputDto);
        return bookingService.addBooking(bookingInputDto, bookerId);
    }

    @PatchMapping("bookings/{bookingId}")
    public BookingDto setApproveStatus(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                       @PathVariable Long bookingId,
                                       @RequestParam Boolean approved) {
        log.info("Booking {} set approved to {} from user {}", bookingId, approved, ownerId);
        return bookingService.setApprovedStatus(bookingId, ownerId, approved);
    }

    @GetMapping("/bookings/owner")
    public List<BookingDto> getBookingsOfOwner(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                               @RequestParam(required = false, defaultValue = "ALL") String state,
                                               @RequestParam(required = false, defaultValue = "0") Integer from,
                                               @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("All bookings of owner {}, status {}", ownerId, state);
        BookingRequestParamsDto bookingRequestParamsDto = BookingRequestParamsDto.builder()
                .ownerId(ownerId)
                .statusString(state)
                .userType("owner")
                .from(from)
                .size(size)
                .build();
        return bookingService.getBookingsOfUser(bookingRequestParamsDto);
    }

    @GetMapping("/bookings/{bookingId}")
    public BookingDto getBookingById(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long userId,
                                     @PathVariable Long bookingId) {
        log.info("Booking of user {} is requested", userId);
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping("/bookings")
    public List<BookingDto> getBookingsOfUser(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                              @RequestParam(required = false, defaultValue = "ALL") String state,
                                              @RequestParam(required = false, defaultValue = "0") Integer from,
                                              @RequestParam(required = false, defaultValue = "10") Integer size) {
        log.info("All bookings of user {}, status {}", ownerId, state);
        BookingRequestParamsDto bookingRequestParamsDto = BookingRequestParamsDto.builder()
                .ownerId(ownerId)
                .statusString(state)
                .userType("booker")
                .from(from)
                .size(size)
                .build();
        return bookingService.getBookingsOfUser(bookingRequestParamsDto);
    }
}