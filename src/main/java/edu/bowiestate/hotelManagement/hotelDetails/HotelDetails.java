package edu.bowiestate.hotelManagement.hotelDetails;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "HOTEL_DETAILS")
public class HotelDetails {

    @Id
    @TableGenerator(name = "Details_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Details_Gen")
    @Column(name = "ID")
    private long id;

    @Column(name = "DAYS_OF_OPERATION")
    private String daysOfOperation;

    @Column(name = "HOURS_OF_OPERATION")
    private String hoursOfOperation;

    @Column(name = "SINGLE_ROOM_PRICE")
    private Double singleRoomPrice;

    @Column(name = "DOUBLE_ROOM_PRICE")
    private Double doubleRoomPrice;

    @Column(name = "SUITE_ROOM_PRICE")
    private Double suiteRoomPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDaysOfOperation() {
        return daysOfOperation;
    }
    public String getStartDayOfOperation() {
        return daysOfOperation.split("-")[0];
    }
    public String getEndDayOfOperation() {
        return daysOfOperation.split("-")[1];
    }

    public void setDaysOfOperation(String daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public String getStartHourOfOperation() {
        return hoursOfOperation.split("-")[0];
    }

    public String getEndHourOfOperation() {
        return hoursOfOperation.split("-")[1];
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public Double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(Double singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public Double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(Double doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public Double getSuiteRoomPrice() {
        return suiteRoomPrice;
    }

    public void setSuiteRoomPrice(Double suiteRoomPrice) {
        this.suiteRoomPrice = suiteRoomPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelDetails that = (HotelDetails) o;
        return id == that.id && daysOfOperation.equals(that.daysOfOperation) && hoursOfOperation.equals(that.hoursOfOperation) && singleRoomPrice.equals(that.singleRoomPrice) && doubleRoomPrice.equals(that.doubleRoomPrice) && suiteRoomPrice.equals(that.suiteRoomPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, daysOfOperation, hoursOfOperation, singleRoomPrice, doubleRoomPrice, suiteRoomPrice);
    }

    public static enum DaysOfOperation {
        MON, TUES, WED, THUR, FRI, SAT, SUN
    }
}
