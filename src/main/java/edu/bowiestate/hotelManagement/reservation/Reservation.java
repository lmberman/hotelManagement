package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.customer.Customer;
import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @TableGenerator(name = "Reservation_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 12348, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Reservation_Gen")
    @Column(name="CONFIRM_NUM")
    private long confirmNum;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ROOM_NUM", nullable = false)
    private Room room;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name="START_DATE")
    private Date startDate;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name="END_DATE")
    private Date endDate;

    @Column(name="PRICE_LOCKED")
    private char priceLocked;

    @Column(name="DO_NOT_DISTURB")
    private char doNotDisturb;

    @Column(name="PRICE")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    public long getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(long confirmNum) {
        this.confirmNum = confirmNum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartDateString() {
        return DateTimeFormatter.ISO_DATE.format(startDate.toInstant());
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndDateString() {
        return DateTimeFormatter.ISO_DATE.format(endDate.toInstant());
    }

    public char isPriceLocked() {
        return priceLocked;
    }

    public void setPriceLocked(char priceLocked) {
        this.priceLocked = priceLocked;
    }

    public char isDoNotDisturb() {
        return doNotDisturb;
    }

    public void setDoNotDisturb(char doNotDisturb) {
        this.doNotDisturb = doNotDisturb;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public enum ReservationStatus {
        OPEN, IN_PROGRESS, CLOSED, CANCELLED
    }

}
