package edu.bowiestate.hotelManagement.hotelDetails;

public class HotelDetailsOutput {
    private Long id;
    private String startDayOfOperation;
    private String endDayOfOperation;
    private String startHourOfOperation;
    private String endHourOfOperation;
    private Double singleRoomPrice;
    private Double doubleRoomPrice;
    private Double suiteRoomPrice;

    public HotelDetailsOutput() {

    }
    public HotelDetailsOutput(HotelDetails hotelDetails){
        this.id = hotelDetails.getId();
        this.startDayOfOperation = hotelDetails.getStartDayOfOperation();
        this.endDayOfOperation = hotelDetails.getEndDayOfOperation();
        this.startHourOfOperation = hotelDetails.getStartHourOfOperation();
        this.endHourOfOperation = hotelDetails.getEndHourOfOperation();
        this.singleRoomPrice = hotelDetails.getSingleRoomPrice();
        this.doubleRoomPrice = hotelDetails.getDoubleRoomPrice();
        this.suiteRoomPrice = hotelDetails.getSuiteRoomPrice();
    }

    public String getStartDayOfOperation() {
        return startDayOfOperation;
    }

    public void setStartDayOfOperation(String startDayOfOperation) {
        this.startDayOfOperation = startDayOfOperation;
    }

    public String getEndDayOfOperation() {
        return endDayOfOperation;
    }

    public void setEndDayOfOperation(String endDayOfOperation) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
