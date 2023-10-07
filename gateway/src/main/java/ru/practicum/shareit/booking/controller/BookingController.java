package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.client.BookingClient;
import ru.practicum.shareit.booking.dto.BookingInputDto;
import ru.practicum.shareit.booking.dto.BookingRequestParamsDto;
import ru.practicum.shareit.booking.validation.BookingCreate;
import ru.practicum.shareit.validators.PageValidator;

@RestController
@RequiredArgsConstructor
@Validated
public class BookingController {

    private final BookingClient bookingClient;
    private final PageValidator pageValidator;

    @PostMapping("/bookings")
    public ResponseEntity<Object> addBooking(@BookingCreate @RequestBody BookingInputDto bookingInputDto,
                                             @RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long bookerId) {
        return bookingClient.addBooking(bookingInputDto, bookerId);
    }

    @PatchMapping("bookings/{bookingId}")
    public ResponseEntity<Object> setApproveStatus(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                                   @PathVariable Long bookingId,
                                                   @RequestParam Boolean approved) {
        return bookingClient.setApproveStatus(ownerId, bookingId, approved);
    }

    @GetMapping("/bookings/owner")
    public ResponseEntity<Object> getBookingsOfOwner(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                                     @RequestParam(required = false, defaultValue = "ALL") String state,
                                                     @RequestParam(required = false, defaultValue = "0") Integer from,
                                                     @RequestParam(required = false, defaultValue = "10") Integer size) {
        pageValidator.validatePagination(from, size);
        BookingRequestParamsDto bookingRequestParamsDto = BookingRequestParamsDto.builder()
                .ownerId(ownerId)
                .statusString(state)
                .userType("owner")
                .from(from)
                .size(size)
                .build();
        return bookingClient.getBookingsOfOwner(bookingRequestParamsDto);
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<Object> getBookingById(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long userId,
                                                 @PathVariable Long bookingId) {
        return bookingClient.getBookingById(userId, bookingId);
    }

    @GetMapping("/bookings")
    public ResponseEntity<Object> getBookingsOfUser(@RequestHeader(BookingControllerConstants.X_SHARER_USER_ID) Long ownerId,
                                                    @RequestParam(required = false, defaultValue = "ALL") String state,
                                                    @RequestParam(required = false, defaultValue = "0") Integer from,
                                                    @RequestParam(required = false, defaultValue = "10") Integer size) {
        pageValidator.validatePagination(from, size);
        BookingRequestParamsDto bookingRequestParamsDto = BookingRequestParamsDto.builder()
                .ownerId(ownerId)
                .statusString(state)
                .userType("booker")
                .from(from)
                .size(size)
                .build();
        return bookingClient.getBookingsOfUser(bookingRequestParamsDto);
    }
}