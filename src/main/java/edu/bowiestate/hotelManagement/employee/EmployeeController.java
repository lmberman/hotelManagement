package edu.bowiestate.hotelManagement.employee;

import edu.bowiestate.hotelManagement.person.Person;
import edu.bowiestate.hotelManagement.person.PersonInput;
import edu.bowiestate.hotelManagement.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private PersonService personService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/newEmployee")
    public String getNewEmployeePage(Model model) {
        model.addAttribute("personInput", new PersonInput());
        return "newEmployeeDetailsPage";
    }

    @GetMapping("/newEmployee/{employeeId}/credentials")
    public String getEmployeeCredentialsPage(@PathVariable Long employeeId, Model model) {
        EmployeeCredentialsForm form = new EmployeeCredentialsForm();
        form.setEmployeeId(employeeId);
        model.addAttribute("employeeCredentialsForm", form);
        return "employeeCredentialsPage";
    }

    @GetMapping("/newEmployee/{employeeId}/creation/confirm")
    public String newEmployeeSaveConfirm(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.findByEmployeeId(employeeId));
        return "newEmployeeSaveConfirm";
    }

    @PostMapping("/newEmployee/details")
    public String saveNewEmployeeDetails(@Valid PersonInput personInput, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Person person = personService.saveNewPerson(personInput);
            if (person != null) {
                return "redirect:/newEmployee/" + person.getId() + "/credentials";
            }
        }
        return "redirect:/newEmployee";
    }

    @PostMapping("/newEmployee/credentials")
    public String saveNewEmployeeCredentials(@Valid EmployeeCredentialsForm employeeCredentialsForm, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Employee employee = employeeService.saveNewEmployee(employeeCredentialsForm);
            if (employee != null) {
                return "redirect:/newEmployee/" +  employeeCredentialsForm.getEmployeeId() + "/creation/confirm";
            }
        }
        return "redirect:/newEmployee/" +  employeeCredentialsForm.getEmployeeId() + "/credentials";
    }
}
