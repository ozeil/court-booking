package de.zeilfelder.tc.courtbooking.booking;

import de.zeilfelder.tc.courtbooking.entities.Booking;
import de.zeilfelder.tc.courtbooking.entities.Court;
import de.zeilfelder.tc.courtbooking.entities.User;
import de.zeilfelder.tc.courtbooking.repositories.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public void makeBooking(BookingRequestDto bookingRequest, User user) {
        var booking = new Booking(user, null, bookingRequest.getSelectedCourt(), bookingRequest.getSelectedTime().toLocalDate(),
                bookingRequest.getSelectedTime().toLocalTime(), bookingRequest.getSelectedDuration());
        bookingRepository.save(booking);
    }

    public Map<Court, Map<Integer, BookingDto>> getBookingsByCourt(LocalDate currentDate) {
        List<Booking> bookingsForDate = bookingRepository.findByDate(currentDate);
        return getBookingsByCourt(bookingsForDate);
    }

    private Map<Court, Map<Integer, BookingDto>> getBookingsByCourt(List<Booking> bookings) {
        Map<Court, Map<Integer, BookingDto>> courtBookingsMap = new EnumMap<>(Court.class);
        for (Court court : Court.values()) {
            courtBookingsMap.put(court, new HashMap<>());
        }

        for (Booking booking : bookings) {
            var bookingDto = convertBookingToDto(booking);
            var map = courtBookingsMap.get(booking.getCourt());
            map.put(bookingDto.hour(), bookingDto);
        }
        return courtBookingsMap;
    }

    private BookingDto convertBookingToDto(Booking booking) {
        return new BookingDto(
                booking.getUser().getFirstname() + " " + booking.getUser().getLastname(),
                booking.getStartTime().getHour(),
                booking.getDuration()
        );
    }
}
