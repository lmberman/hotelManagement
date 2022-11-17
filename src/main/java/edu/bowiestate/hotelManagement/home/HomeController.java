package edu.bowiestate.hotelManagement.home;

import edu.bowiestate.hotelManagement.employee.Employee;
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

    @GetMapping({"/login","/"})
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_RECEPTION','ROLE_HOUSEKEEP')")
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        // check roles and return home page based on role
        Optional<GrantedAuthority> loggedInUserRole = (Optional<GrantedAuthority>) authentication.getAuthorities().stream().findFirst();
        if(loggedInUserRole.isPresent()) {
            String role = loggedInUserRole.get().getAuthority();

                if(role.equals(Employee.EmployeeRole.ROLE_MANAGER)){
                    return "managerHome";
                } else if (role.equals(Employee.EmployeeRole.ROLE_ROLE_RECEPTION)) {
                    return "receptionHome";
                } else {
                    return "houseKeepHome";
                }

        }
        return null;
    }

}
