package edu.bowiestate.hotelManagement.housekeep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseKeepTaskService {

    @Autowired
    private HouseKeepRepository houseKeepRepository;

    public List<HouseKeepTask> getAllInCompleteTasks() {
        return houseKeepRepository.findAllInCompleteTasks();
    }

//    public List<HouseKeepTask> getAllCurrentDayTasks(){
//
//    }
}
