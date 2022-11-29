package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HouseKeepingController {

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    @Autowired
    private RoomService roomService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/houseKeepingTasks")
    public String getHouseKeepingTasksPage(Model model){
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("houseKeepTaskForm", new HouseKeepTaskForm());
        model.addAttribute("completedTasks",houseKeepTaskService.getAllCompleteTasks());
        model.addAttribute("inProgressTasks",houseKeepTaskService.getAllInCompleteTasks());
        return "houseKeepingTasksList";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @PostMapping("/houseKeep/addTask")
    public String addTaskToRoom(HouseKeepTaskForm houseKeepTaskForm, Model model) {
        houseKeepTaskService.saveHouseKeepTask(houseKeepTaskForm);
        return "redirect:/houseKeepingTasks";
    }

    @PreAuthorize("hasAnyRole('ROLE_HOUSEKE')")
    @GetMapping("/houseKeepTask/{id}/claim")
    public String claimHouseKeepTask(@PathVariable Long id, Principal principal){
        houseKeepTaskService.assignTaskToEmployee(id, principal.getName());
        return "redirect:/home";
    }

    @PreAuthorize("hasAnyRole('ROLE_HOUSEKE')")
    @GetMapping("/houseKeepTask/{id}/update")
    public String updateTask(@PathVariable Long id, Model model) {
        model.addAttribute("houseKeepTask",houseKeepTaskService.findById(id));
        return "houseKeepTaskUpdate";
    }
}
