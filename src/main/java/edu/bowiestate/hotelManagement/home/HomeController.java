package edu.bowiestate.hotelManagement.home;

import edu.bowiestate.hotelManagement.employee.Employee;
import edu.bowiestate.hotelManagement.housekeep.HouseKeepTaskService;
import edu.bowiestate.hotelManagement.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController{

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private HouseKeepTaskService houseKeepTaskService;

    @GetMapping({"/login","/"})
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPT','ROLE_HOUSEKE')")
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        // check roles and return home page based on role
        Optional<GrantedAuthority> loggedInUserRole = (Optional<GrantedAuthority>) authentication.getAuthorities().stream().findFirst();
        if(loggedInUserRole.isPresent()) {
            String role = loggedInUserRole.get().getAuthority();

                if(role.equals(Employee.EmployeeRole.MANAGER.getNameWithPrefix())){
                    model.addAttribute("houseKeepingTasks", houseKeepTaskService.getAllInCompleteTasks());
                    model.addAttribute("reservations",reservationService.findCurrentDaysReservations());
                    return "managerHome";
                } else if (role.equals(Employee.EmployeeRole.RECEPT.getNameWithPrefix())) {
                    model.addAttribute("houseKeepingTasks", houseKeepTaskService.getAllInCompleteTasks());
                    model.addAttribute("reservations",reservationService.findCurrentDaysReservations());
                    return "receptionHome";
                } else {
                    model.addAttribute("ownedTasks", houseKeepTaskService.getTasksForUser(authentication.getName()));
                    model.addAttribute("unownedTasks", houseKeepTaskService.getAllUnownedTasks());
                    return "houseKeepingHome";
                }

        }
        return null;
    }

}
