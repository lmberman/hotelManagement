package edu.bowiestate.hotelManagement.hotelDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelDetailsService {

    @Autowired
    private HotelDetailsRepository hotelDetailsRepository;

    public HotelDetailsOutput findCurrentHotelDetails(){
        List<HotelDetails> hotelDetailsList = hotelDetailsRepository.findAll();
        if(!hotelDetailsList.isEmpty()){
            HotelDetails hotelDetails = hotelDetailsList.get(0);
            HotelDetailsOutput output = new HotelDetailsOutput(hotelDetails);
            return output;
        } else {
            return new HotelDetailsOutput();
        }
    }

    public HotelDetails updateHotelDetails(HotelDetailsForm hotelDetailsForm) {
        HotelDetails hotelDetail = hotelDetailsRepository.findAll().get(0);
        hotelDetail.setDaysOfOperation(hotelDetailsForm.getStartDayOfOperation() + "-" + hotelDetailsForm.getEndDayOfOperation());
        hotelDetail.setHoursOfOperation(hotelDetailsForm.getStartHourOfOperation() + "-" + hotelDetailsForm.getEndHourOfOperation());

        hotelDetail.setSingleRoomPrice(hotelDetailsForm.getSingleRoomPrice());

        hotelDetail.setDoubleRoomPrice(hotelDetailsForm.getDoubleRoomPrice());
        hotelDetail.setSuiteRoomPrice(hotelDetailsForm.getSuiteRoomPrice());
        hotelDetailsRepository.save(hotelDetail);
        // throw exception
       return null;
    }
}
