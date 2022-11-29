package edu.bowiestate.hotelManagement.employee;

import edu.bowiestate.hotelManagement.person.Person;
import edu.bowiestate.hotelManagement.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonService personService;

    public Employee findByEmployeeId(Long employeeId){
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        return null;
    }

    public Employee saveNewEmployee(EmployeeCredentialsForm employeeCredentialsForm) {
        Person person = personService.findByPersonById(employeeCredentialsForm.getEmployeeId());
        if(person != null) {
            Employee employee = new Employee();
            employee.setEmployeeId(employee.getEmployeeId());
            employee.setPassword(new BCryptPasswordEncoder(10).encode(employeeCredentialsForm.getPassword()));
            employee.setRole(employeeCredentialsForm.getEmployeeRole());
            employee.setPerson(person);
            employee.setCreatedDate(LocalDate.now());
            employee.setLastModifiedDate(LocalDate.now());
            return employeeRepository.save(employee);
        }
        return null;
    }
}
