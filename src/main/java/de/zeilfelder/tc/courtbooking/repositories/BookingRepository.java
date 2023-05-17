package de.zeilfelder.tc.courtbooking.repositories;

import de.zeilfelder.tc.courtbooking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
