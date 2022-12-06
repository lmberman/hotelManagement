package edu.bowiestate.hotelManagement.reservation;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class ReservationUpdateForm {

    @NotNull
    private Long confirmNum;

    @NotNull
    private Long roomNum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date endDate;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
