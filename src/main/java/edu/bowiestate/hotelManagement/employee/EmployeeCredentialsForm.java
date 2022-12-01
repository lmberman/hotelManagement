package edu.bowiestate.hotelManagement.employee;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;
import edu.bowiestate.hotelManagement.employee.Employee.EmployeeRole;
import javax.validation.constraints.NotNull;

public class EmployeeCredentialsForm {

    @NotNull
    @Length(min=8, max=8)
    @NumberFormat(style= NumberFormat.Style.NUMBER)
    private String employeeId;
    @NotNull
    @Length(min=8, max=15)
    private String password;

    @NotNull
    private EmployeeRole employeeRole;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }
}
