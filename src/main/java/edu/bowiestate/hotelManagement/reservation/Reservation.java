package edu.bowiestate.hotelManagement.reservation;

import edu.bowiestate.hotelManagement.customer.Customer;
import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
    private Date startDate;

    @Column(name="END_DATE")
    private Date endDate;

    @Column(name="PRICE_LOCKED")
    private boolean priceLocked;

    @Column(name="DO_NOT_DISTURB")
    private boolean doNotDisturb;

    @Column(name="PRICE")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private ReservationStatus status;

    private enum ReservationStatus {
        OPEN,CLOSED,CANCELLED
    }

}
