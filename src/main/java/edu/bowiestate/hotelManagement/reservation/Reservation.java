package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.customer.Customer;
import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="RESERVATION")
public class Reservation {
    @Id
    @TableGenerator(name = "Reservation_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1000, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Reservation_Gen")
    @Column(name="CONFIRM_NUM")
    private long confirmNum;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ROOM_NUM", nullable = false)
    private Room room;

    @Column(name="START_DATE")
    private LocalDate startDate;

    @Column(name="END_DATE")
    private LocalDate endDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    enum ReservationStatus {
        OPEN, IN_PROGRESS, CLOSED, CANCELLED
    }

}
