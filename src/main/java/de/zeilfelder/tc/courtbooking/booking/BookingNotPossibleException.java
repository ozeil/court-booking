package de.zeilfelder.tc.courtbooking.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The booking parameters are in conflict with another booking.")
public final class BookingNotPossibleException extends RuntimeException {

    public BookingNotPossibleException(String s) {
        super(s);
    }
}
