package edu.bowiestate.hotelManagement.hotelDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HotelDetailsController {

    @Autowired
    private HotelDetailsService hotelDetailsService;

    @GetMapping("/hotelDetails")
    public String getHotelDetails(Model model){
        HotelDetailsOutput currentHotelDetails = hotelDetailsService.findCurrentHotelDetails();
        HotelDetailsForm hotelDetailsForm = new HotelDetailsForm();
        hotelDetailsForm.setId(currentHotelDetails.getId());
        model.addAttribute("currentHotelDetails", currentHotelDetails);
        model.addAttribute("hotelDetailsForm", hotelDetailsForm);
        return "manageHotelDetails";
    }

    @PostMapping("/hotelDetails/update")
    public String updateHotelDetails(HotelDetailsForm hotelDetailsForm, Model model){
        hotelDetailsService.updateHotelDetails(hotelDetailsForm);
        return "manageHotelDetails";
    }
}
