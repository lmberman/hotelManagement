package edu.bowiestate.hotelManagement.hotelDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HotelDetailsController {

    @Autowired
    private HotelDetailsService hotelDetailsService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hotelDetails")
    public String getHotelDetails(Model model){
        HotelDetailsOutput currentHotelDetails = hotelDetailsService.findCurrentHotelDetails();
        model.addAttribute("currentHotelDetails", currentHotelDetails);
        model.addAttribute("hotelDetailsForm", new HotelDetailsForm());
        return "manageHotelDetails";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/hotelDetails/update")
    public String updateHotelDetails(@Valid HotelDetailsForm hotelDetailsForm, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()) {
           hotelDetailsService.updateHotelDetails(hotelDetailsForm);
           HotelDetailsOutput currentHotelDetails = hotelDetailsService.findCurrentHotelDetails();
           model.addAttribute("currentHotelDetails", currentHotelDetails);
           model.addAttribute("updateSuccessful", true);
           return "redirect:/hotelDetails";
        }

        return "redirect:/hotelDetails";
    }
}
