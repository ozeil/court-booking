package de.zeilfelder.tc.courtbooking.booking;

import java.time.Duration;

public record BookingDto(String username, int hour, Duration duration) {
}
