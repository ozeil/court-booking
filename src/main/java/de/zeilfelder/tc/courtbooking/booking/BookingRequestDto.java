package de.zeilfelder.tc.courtbooking.booking;

import de.zeilfelder.tc.courtbooking.entities.Court;

import java.time.Duration;
import java.time.LocalDateTime;

public class BookingRequestDto {

    private Court selectedCourt;
    private LocalDateTime selectedTime;
    private Duration selectedDuration;

    public BookingRequestDto(Court selectedCourt, LocalDateTime selectedTime, Duration selectedDuration) {
        this.selectedCourt = selectedCourt;
        this.selectedTime = selectedTime;
        this.selectedDuration = selectedDuration;
    }

    public BookingRequestDto() {
    }

    public Court getSelectedCourt() {
        return selectedCourt;
    }

    public void setSelectedCourt(Court selectedCourt) {
        this.selectedCourt = selectedCourt;
    }

    public LocalDateTime getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(LocalDateTime selectedTime) {
        this.selectedTime = selectedTime;
    }

    public Duration getSelectedDuration() {
        return selectedDuration;
    }

    public void setSelectedDuration(Duration selectedDuration) {
        this.selectedDuration = selectedDuration;
    }
}
