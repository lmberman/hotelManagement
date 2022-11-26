package edu.bowiestate.hotelManagement.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(nativeQuery = true, value = "Select * from Reservation where :currentDay between start_date and end_date" +
            " and status in ('OPEN','IN_PROGRESS') ")
    public List<Reservation> findByTodaysReservations(@Param("currentDay") LocalDateTime currentDay);
}
