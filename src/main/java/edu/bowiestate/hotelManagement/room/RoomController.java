package edu.bowiestate.hotelManagement.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/vacancyList")
    public String getVacancyList(Model model){
        model.addAttribute("unoccupiedRooms", roomService.getUnoccupiedRooms());
        model.addAttribute("occupiedRooms", roomService.getOccupiedRooms());
        return "vacancyList";
    }
}
