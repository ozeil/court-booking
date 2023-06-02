package de.zeilfelder.tc.courtbooking.booking;

import de.zeilfelder.tc.courtbooking.entities.Court;
import de.zeilfelder.tc.courtbooking.entities.User;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String showBookingsForDate(
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {
        var bookings = bookingService.getBookingsByCourt(date);
        model.addAttribute("currentDate", date);
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/make-booking")
    public String showBookingForm(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
            @RequestParam Court court, Model model) {
        BookingRequestDto bookingRequest = new BookingRequestDto();
        bookingRequest.setSelectedTime(dateTime);
        bookingRequest.setSelectedCourt(court);
        model.addAttribute("bookingRequest", bookingRequest);
        return "make-booking";
    }

    @PostMapping("/make-booking")
    public String makeBooking(@ModelAttribute @Valid BookingRequestDto bookingRequest, BindingResult bindingResult,
                              @AuthenticationPrincipal User user) throws BookingNotPossibleException {
        // TODO handle errors properly when booking goes wrong
        if (bindingResult.hasErrors()) {
            return "make-booking";
        }
        bookingService.makeBooking(bookingRequest, user);
        return "redirect:/bookings";
    }

    // TODO add delete functionality
    @GetMapping("/delete-booking")
    public String deleteBooking(@RequestParam LocalDateTime dateTime, @RequestParam Court court,
                                @AuthenticationPrincipal User user) throws BookingDeletionException {
        bookingService.deleteBooking(dateTime, court, user);
        return "redirect:/bookings";
    }

    // TODO add blocker functionality (returning/repeating bookings)
}
