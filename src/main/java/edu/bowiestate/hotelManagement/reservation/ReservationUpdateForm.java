package edu.bowiestate.hotelManagement.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationUpdateForm {

    @NotNull
    private Long confirmNum;

    @NotNull
    private Long roomNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    @NotNull
    private double price;

    public Long getConfirmNum() {
        return confirmNum;
    }

    public void setConfirmNum(Long confirmNum) {
        this.confirmNum = confirmNum;
    }

    public Long getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Long roomNum) {
        this.roomNum = roomNum;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
