package de.zeilfelder.tc.courtbooking;

import de.zeilfelder.tc.courtbooking.entities.Booking;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showBookings(Model model) {
        LocalDate currentDate = LocalDate.now();
        List<Booking> bookings = bookingService.getBookingsForDate(currentDate);

        model.addAttribute("currentDate", currentDate);
        model.addAttribute("bookings", bookings);

        return "bookings";
    }

    @GetMapping("/{date}")
    public String showBookingsForDate(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                      Model model) {
        List<Booking> bookings = bookingService.getBookingsForDate(date);

        model.addAttribute("currentDate", date);
        model.addAttribute("bookings", bookings);

        return "bookings";
    }

}
