package edu.bowiestate.hotelManagement.hotelDetails;

import edu.bowiestate.hotelManagement.hotelDetails.HotelDetails.DaysOfOperation;


public class HotelDetailsForm {
    private DaysOfOperation startDayOfOperation;
    private DaysOfOperation endDayOfOperation;
    private String startHourOfOperation;
    private String endHourOfOperation;
    private Double singleRoomPrice;
    private Double doubleRoomPrice;
    private Double suiteRoomPrice;

    public DaysOfOperation getStartDayOfOperation() {
        return startDayOfOperation;
    }

    public void setStartDayOfOperation(DaysOfOperation startDayOfOperation) {
        this.startDayOfOperation = startDayOfOperation;
    }

    public DaysOfOperation getEndDayOfOperation() {
        return endDayOfOperation;
    }

    public void setEndDayOfOperation(DaysOfOperation endDayOfOperation) {
        this.endDayOfOperation = endDayOfOperation;
    }

    public String getStartHourOfOperation() {
        return startHourOfOperation;
    }

    public void setStartHourOfOperation(String startHourOfOperation) {
        this.startHourOfOperation = startHourOfOperation;
    }

    public String getEndHourOfOperation() {
        return endHourOfOperation;
    }

    public void setEndHourOfOperation(String endHourOfOperation) {
        this.endHourOfOperation = endHourOfOperation;
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
}
