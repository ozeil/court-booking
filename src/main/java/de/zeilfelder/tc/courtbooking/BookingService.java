package de.zeilfelder.tc.courtbooking;

import de.zeilfelder.tc.courtbooking.entities.Booking;
import de.zeilfelder.tc.courtbooking.repositories.BookingRepository;
import de.zeilfelder.tc.courtbooking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookingsForDate(LocalDate currentDate) {
        return bookingRepository.findByDate(currentDate);
    }
}
