package edu.bowiestate.hotelManagement.housekeep;

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
import java.security.Principal;

@Controller
public class HouseKeepingController {

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    @Autowired
    private RoomService roomService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @GetMapping("/houseKeepingTasks")
    public String getHouseKeepingTasksPage(@RequestParam(required = false) boolean successfulAdd, Model model){
        model.addAttribute("rooms", roomService.getRoomsWithNoPendingOrInProgressTasks());
        model.addAttribute("houseKeepTaskForm", new HouseKeepTaskForm());
        model.addAttribute("completedTasks",houseKeepTaskService.getAllCompleteTasks());
        model.addAttribute("inProgressTasks",houseKeepTaskService.getAllInCompleteTasks());
        if(successfulAdd) {
            model.addAttribute("updateSuccessful", true);
        }
        return "houseKeepingTasksList";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT')")
    @PostMapping("/houseKeep/addTask")
    public String addTaskToRoom(@Valid HouseKeepTaskForm houseKeepTaskForm,
                                BindingResult bindingResult, Model model, HttpServletRequest request) {
        if(bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            String targetUrl = request.getHeader("referer");
            return "redirect:" + targetUrl;
        }
        houseKeepTaskService.saveHouseKeepTask(houseKeepTaskForm);
        return "redirect:/houseKeepingTasks?successfulAdd=true";
    }

    @PreAuthorize("hasAnyRole('ROLE_HOUSEKE')")
    @GetMapping("/houseKeepTask/{id}/claim")
    public String claimHouseKeepTask(@PathVariable Long id, Principal principal){
        houseKeepTaskService.assignTaskToEmployee(id, principal.getName());
        return "redirect:/home";
    }

    @PreAuthorize("hasAnyRole('ROLE_HOUSEKE')")
    @PostMapping("/houseKeepTask/{id}/update")
    public String updateTask(@PathVariable Long id, @Valid HouseKeepTaskUpdateForm houseKeepTaskUpdateForm, BindingResult bindingResult,
                             Model model, HttpServletRequest request) {

        houseKeepTaskService.updateHouseKeepTask(id, houseKeepTaskUpdateForm);
        String targetUrl = request.getHeader("referer");
        return "redirect:" + targetUrl;
    }
}
