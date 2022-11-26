package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    }

    public List<Reservation> findCurrentDaysReservations() {
        return reservationRepository.findByTodaysReservations(LocalDateTime.now());
    }

    public Reservation findByConfirmationNum(Long confirmId) {
        Optional<Reservation> reservation = reservationRepository.findById(confirmId);
        if (reservation.isPresent()) {
            return reservation.get();
        } else {
            return null;
        }
    }

    public Reservation checkIn(Long confirmId) {
        Reservation reservation = findByConfirmationNum(confirmId);
        if (reservation != null) {
            reservation.setPriceLocked(true);
            reservation.setStatus(Reservation.ReservationStatus.IN_PROGRESS);
            reservation.getRoom().setStatus(Room.RoomStatus.OCCUPIED);
            return reservationRepository.save(reservation);
        } else {
            // error
            return null;
        }
    }

    public Reservation checkout(Long confirmId) {
        Reservation reservation = findByConfirmationNum(confirmId);
        if (reservation != null) {
            reservation.setStatus(Reservation.ReservationStatus.CLOSED);
            reservation.getRoom().setStatus(Room.RoomStatus.SERVICE_REQ);
            return reservationRepository.save(reservation);
        } else {
            // error
            return null;
        }
    }
}
