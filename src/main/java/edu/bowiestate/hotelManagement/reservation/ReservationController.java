package edu.bowiestate.hotelManagement.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation")
    public List<Reservation> getAllReservations() {
        return reservationService.findAllReservations();
    }

    @GetMapping("/reservation/current")
    public List<Reservation> getCurrentDaysReservations() {
        return reservationService.findCurrentDaysReservations();
    }

    @GetMapping("/reservation/{id}")
    public Reservation getReservationByConfirmationNum(@PathVariable long id){
        return reservationService.findByConfirmationNum(id);
    }

    @PostMapping("/reservation/checkIn/{id}")
    public Reservation checkIn(@PathVariable long id){
        return reservationService.checkIn(id);
    }

    @PostMapping("/reservation/checkout/{id}")
    public Reservation checkOut(@PathVariable long id){
        return reservationService.checkout(id);
    }

}
