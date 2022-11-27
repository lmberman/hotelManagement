package edu.bowiestate.hotelManagement.housekeep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HouseKeepingController {

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    @GetMapping("/houseKeepingTasks")
    public String getHouseKeepingTasksPage(Model model){
        model.addAttribute("completedTasks",houseKeepTaskService.getAllCompleteTasks());
        model.addAttribute("inProgressTasks",houseKeepTaskService.getAllInCompleteTasks());
        return "houseKeepingTasksList";
    }
}
