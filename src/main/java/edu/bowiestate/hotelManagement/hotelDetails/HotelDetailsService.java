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
        Optional<HotelDetails> hotelDetails = hotelDetailsRepository.findById(hotelDetailsForm.getId());
        if(hotelDetails.isPresent()){
            HotelDetails existingHotelDetails = hotelDetails.get();
            existingHotelDetails.setDaysOfOperation(hotelDetailsForm.getStartDayOfOperation() + "-" + hotelDetailsForm.getEndDayOfOperation());
            existingHotelDetails.setHoursOfOperation(hotelDetailsForm.getStartHourOfOperation() + "-" + hotelDetailsForm.getEndHourOfOperation());

            existingHotelDetails.setSingleRoomPrice(hotelDetailsForm.getSingleRoomPrice());

            existingHotelDetails.setDoubleRoomPrice(hotelDetailsForm.getDoubleRoomPrice());
            existingHotelDetails.setSuiteRoomPrice(hotelDetailsForm.getSuiteRoomPrice());
            hotelDetailsRepository.save(existingHotelDetails);
        }
        // throw exception
       return null;
    }
}
