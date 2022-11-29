package edu.bowiestate.hotelManagement.employee;

public class EmployeeCredentialsForm {
    private Long employeeId;
    private String password;
    private Employee.EmployeeRole employeeRole;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee.EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(Employee.EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }
}
