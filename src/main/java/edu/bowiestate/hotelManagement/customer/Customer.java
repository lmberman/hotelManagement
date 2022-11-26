package edu.bowiestate.hotelManagement.customer;

import edu.bowiestate.hotelManagement.employee.Employee;
import edu.bowiestate.hotelManagement.reservation.Reservation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @TableGenerator(name = "Cust_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Cust_Gen")
    @Column(name="CUST_ID")
    private long custId;

    @Column(name = "LICENSE_PLATE_NUM")
    private String licensePlateNum;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHECKED_IN_BY", referencedColumnName = "EMPLOYEE_ID")
    private Employee checkedInBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHECKED_OUT_BY", referencedColumnName = "EMPLOYEE_ID")
    private Employee checkedOutBy;

    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservations;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getLicensePlateNum() {
        return licensePlateNum;
    }

    public void setLicensePlateNum(String licensePlateNum) {
        this.licensePlateNum = licensePlateNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Employee getCheckedInBy() {
        return checkedInBy;
    }

    public void setCheckedInBy(Employee checkedInBy) {
        this.checkedInBy = checkedInBy;
    }

    public Employee getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(Employee checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return custId == customer.custId && Objects.equals(licensePlateNum, customer.licensePlateNum) && emailAddress.equals(customer.emailAddress) && Objects.equals(checkedInBy, customer.checkedInBy) && Objects.equals(checkedOutBy, customer.checkedOutBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custId, licensePlateNum, emailAddress, checkedInBy, checkedOutBy);
    }
}
