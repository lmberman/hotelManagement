package edu.bowiestate.hotelManagement.employee;

import edu.bowiestate.hotelManagement.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
