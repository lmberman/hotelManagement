package edu.bowiestate.hotelManagement.employee;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @TableGenerator(name = "Employee_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 5, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Employee_Gen")
    @Column(name="EMPLOYEE_ID")
    private long employeeId;

    @Column(name="PASSWORD")
    private String password;

    @JoinColumn(name = "ROLE")
    private EmployeeRole role;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private Date lastModifiedDate;

    // add created date and last modified date to this table

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, password, role);
    }

    public enum EmployeeRole {
        ROLE_MANAGER("MANAGER"),
        ROLE_ROLE_RECEPTION("ROLE_RECEPTION"),
        ROLE_HOUSEKEEP("HOUSE_KEEP");

        private String nameWithoutPrefix;

        EmployeeRole(String nameWithoutPrefix) {
            this.nameWithoutPrefix = nameWithoutPrefix;
        }

        public String getNameWithoutPrefix() {
            return nameWithoutPrefix;
        }

        public void setNameWithoutPrefix(String nameWithoutPrefix) {
            this.nameWithoutPrefix = nameWithoutPrefix;
        }
    }
}
