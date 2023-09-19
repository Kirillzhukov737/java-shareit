package ru.practicum.shareit.booking.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.dto.BookerDtoInItem;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInputDto;

import java.time.ZoneOffset;
import java.util.Date;

@Component
public class BookingMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public BookingMapper() {
        this.modelMapper = new ModelMapper();
        Configuration configuration = modelMapper.getConfiguration();
        configuration.setFieldAccessLevel(Configuration.AccessLevel.PUBLIC);
        configuration.setSourceNamingConvention(NamingConventions.JAVABEANS_ACCESSOR);
        configuration.setDestinationNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        configuration.setSourceNameTokenizer(NameTokenizers.CAMEL_CASE);
        configuration.setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * Выполняет преобразование объекта Booking в объект BookingDto.
     */
    public BookingDto convertToBookingDto(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDto bookingDto = modelMapper.map(booking, BookingDto.class);
        bookingDto.setStart(Date.from(booking.getStart()));
        bookingDto.setEnd(Date.from(booking.getEnd()));
        return bookingDto;
    }

    /**
     * Выполняет преобразование объекта BookingDto в объект Booking.
     */

    public Booking convertToBooking(BookingDto bookingDto) {
        return modelMapper.map(bookingDto, Booking.class);
    }

    /**
     * Выполняет преобразование объекта BookingInputDto в объект Booking.
     */
    public Booking convertToBooking(BookingInputDto bookingInputDto) {
        Booking booking = modelMapper.map(bookingInputDto, Booking.class);
        booking.setStart(bookingInputDto.getStart().toInstant(ZoneOffset.ofHours(0)));
        booking.setEnd(bookingInputDto.getEnd().toInstant(ZoneOffset.ofHours(0)));
        return booking;
    }

    /**
     * Выполняет преобразование объекта Booking в объект BookerDtoInItem.
     */
    public BookerDtoInItem convertToBookingDtoInItem(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookerDtoInItem bookerDtoInItem = modelMapper.map(booking, BookerDtoInItem.class);
        bookerDtoInItem.setBookerId(booking.getBooker().getId());
        return bookerDtoInItem;
    }
}