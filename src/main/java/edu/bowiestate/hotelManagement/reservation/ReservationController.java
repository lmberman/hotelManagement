package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/current")
    public String getCurrentDaysReservations(Model model) {
        model.addAttribute("reservations",reservationService.findCurrentDaysReservations());
        return "currentReservations";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/upcoming")
    public String getUpcomingReservations(Model model) {
        model.addAttribute("reservations",reservationService.findFutureReservations());
        return "upcomingReservations";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/{id}/checkIn")
    public String checkIn(@PathVariable long id, HttpServletRequest request){
        // need to direct to payment page before we get here
        reservationService.checkIn(id);
        String targetUrl = request.getHeader("referer");
        return "redirect:" + targetUrl;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/{id}/checkout")
    public String checkOut(@PathVariable long id, HttpServletRequest request){
        reservationService.checkout(id);
        String targetUrl = request.getHeader("referer");
        return "redirect:" + targetUrl;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/{id}/cancel")
    public String cancel(@PathVariable long id,HttpServletRequest request){
        reservationService.cancel(id);
        String targetUrl = request.getHeader("referer");
        return "redirect:" + targetUrl;
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/reservation/{id}/update")
    public String getUpdatePage(@PathVariable long id,@RequestParam(required = false) boolean updateSuccessful, Model model){
        ReservationUpdateForm form = new ReservationUpdateForm();
        form.setConfirmNum(id);
        model.addAttribute("reservation",reservationService.findByConfirmationNum(id));
        model.addAttribute("reservationUpdateForm", form);
        model.addAttribute("availableRooms", roomService.getAllAvailableRooms());
        if(updateSuccessful) {
            model.addAttribute("updateSuccessful", true);
        }
        return "reservationUpdate";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @PostMapping("/reservation/update")
    public String updateReservation(@Valid ReservationUpdateForm reservationUpdateForm, BindingResult bindingResult, Model model, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            String targetUrl = request.getHeader("referer");
            return "redirect:" + targetUrl;
        }

        reservationService.updateReservation(reservationUpdateForm);
        return "redirect:/reservation/"+ reservationUpdateForm.getConfirmNum() + "/update?updateSuccessful=true";
    }

}
