package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HouseKeepingController {

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/houseKeepingTasks")
    public String getHouseKeepingTasksPage(Model model){
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("houseKeepTaskForm", new HouseKeepTaskForm());
        model.addAttribute("completedTasks",houseKeepTaskService.getAllCompleteTasks());
        model.addAttribute("inProgressTasks",houseKeepTaskService.getAllInCompleteTasks());
        return "houseKeepingTasksList";
    }

    @PostMapping("/houseKeep/addTask")
    public String addTaskToRoom(HouseKeepTaskForm houseKeepTaskForm, Model model) {
        houseKeepTaskService.saveHouseKeepTask(houseKeepTaskForm);
        return "houseKeepingTasksList";
    }
}
