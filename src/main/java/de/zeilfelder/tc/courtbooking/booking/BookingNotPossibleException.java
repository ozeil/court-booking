package de.zeilfelder.tc.courtbooking.booking;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The booking parameters are in conflict with another booking.")
public final class BookingNotPossibleException extends RuntimeException {
    public BookingNotPossibleException() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "BookingNotPossibleException[]";
    }

}
