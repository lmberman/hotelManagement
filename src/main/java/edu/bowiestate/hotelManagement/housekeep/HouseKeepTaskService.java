package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.room.Room;
import edu.bowiestate.hotelManagement.room.RoomRepository;
import edu.bowiestate.hotelManagement.room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HouseKeepTaskService {

    @Autowired
    private HouseKeepRepository houseKeepRepository;

    @Autowired
    private RoomService roomService;

    public List<HouseKeepTask> getAllInCompleteTasks() {
        return houseKeepRepository.findAllByStatusNotOrderByCreatedDate(HouseKeepTask.TaskStatus.COMPLETE);
    }

    public List<HouseKeepTask> getAllCompleteTasks() {
        return houseKeepRepository.findAllByStatusOrderByCompletionDate(HouseKeepTask.TaskStatus.COMPLETE);
    }

    public HouseKeepTask saveHouseKeepTask(HouseKeepTaskForm houseKeepTaskForm) {
       Room room = roomService.findById(houseKeepTaskForm.getTaskRoomNum());
       if(room == null) {
           //error of some kind
           return null;
       }
       room.setStatus(Room.RoomStatus.SERVICE_REQ);
       room = roomService.save(room);

       HouseKeepTask houseKeepTask = new HouseKeepTask();
       houseKeepTask.setRoom(room);
       houseKeepTask.setType(houseKeepTaskForm.getTaskType());
       houseKeepTask.setStatus(HouseKeepTask.TaskStatus.PENDING);
       houseKeepTask.setDeadlineDate(houseKeepTaskForm.getDeadlineDate());
       houseKeepTask.setCreatedDate(LocalDate.now());
       return houseKeepRepository.save(houseKeepTask);
    }
}
