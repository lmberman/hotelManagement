package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.housekeep.HouseKeepTask;
import edu.bowiestate.hotelManagement.housekeep.HouseKeepTaskForm;
import edu.bowiestate.hotelManagement.housekeep.HouseKeepTaskService;
import edu.bowiestate.hotelManagement.room.Room;
import edu.bowiestate.hotelManagement.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll(Sort.by(Sort.Direction.DESC, "startDate"));
    }

    public List<Reservation> findCurrentDaysReservations() {
        return reservationRepository.findByTodaysReservations(LocalDate.now());
    }

    public List<Reservation> findFutureReservations() {
        return reservationRepository.findByStartDateGreaterThanAndStatusOrderByStartDate(LocalDate.now(), Reservation.ReservationStatus.OPEN);
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
            reservation.setPriceLocked('Y');
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

            HouseKeepTaskForm taskForm = new HouseKeepTaskForm();
            taskForm.setTaskType(HouseKeepTask.TaskType.CLEANING);
            taskForm.setTaskRoomNum(reservation.getRoom().getRoomNum());
            taskForm.setDeadlineDate(LocalDate.now());

            HouseKeepTask houseKeepTask = houseKeepTaskService.saveHouseKeepTask(taskForm);
            reservation.setRoom(houseKeepTask.getRoom());

            return reservationRepository.save(reservation);
        } else {
            // error
            return null;
        }
    }

    public Reservation cancel(Long confirmId) {
        Reservation reservation = findByConfirmationNum(confirmId);
        if (reservation != null) {
            reservation.setStatus(Reservation.ReservationStatus.CLOSED);
            reservation.getRoom().setStatus(Room.RoomStatus.AVAILABLE);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public Reservation updateReservation(ReservationUpdateForm reservationUpdateForm) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationUpdateForm.getConfirmNum());
        if(reservation.isPresent()){
            Reservation existingReservation = reservation.get();
            Room room = roomService.findById(reservationUpdateForm.getRoomNum());
            if(room != null) {
                if(room.getRoomNum() != existingReservation.getRoom().getRoomNum()) {
                    Room currentRoom = existingReservation.getRoom();
                    HouseKeepTaskForm houseKeepTaskForm = new HouseKeepTaskForm();
                    houseKeepTaskForm.setTaskRoomNum(currentRoom.getRoomNum());
                    houseKeepTaskForm.setTaskType(HouseKeepTask.TaskType.CLEANING);
                    houseKeepTaskForm.setDeadlineDate(LocalDate.now().plusDays(1));
                    houseKeepTaskService.saveHouseKeepTask(houseKeepTaskForm);
                }

                existingReservation.setRoom(room);
                existingReservation.setStartDate(reservationUpdateForm.getStartDate());
                existingReservation.setEndDate(reservationUpdateForm.getEndDate());
                return reservationRepository.save(existingReservation);
            }

        }
        return null;
    }
}
