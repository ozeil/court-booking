package de.zeilfelder.tc.courtbooking.repositories;

import de.zeilfelder.tc.courtbooking.entities.Booking;
import de.zeilfelder.tc.courtbooking.entities.Court;
import de.zeilfelder.tc.courtbooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByDate(LocalDate date);

    boolean existsBookingByDateAndCourtAndStartTimeBetween(LocalDate date, Court court, LocalTime startTime,
                                                           LocalTime startTime2);

    Optional<Booking> findBookingByUserAndCourtAndDateAndStartTime(User user, Court court, LocalDate date,
                                                                   LocalTime startTime);

    void deleteBookingById(Long id);
}
