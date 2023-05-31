package de.zeilfelder.tc.courtbooking.booking;

import de.zeilfelder.tc.courtbooking.entities.Court;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;

import java.time.Duration;
import java.time.LocalDateTime;

public class BookingRequestDto {
    @NotNull
    private Court selectedCourt;
    @NotNull
    private LocalDateTime selectedTime;
    @DurationMin(hours = 1)
    @DurationMax(hours = 2)
    private Duration selectedDuration;

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
