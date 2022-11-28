package edu.bowiestate.hotelManagement.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getUnoccupiedRooms() {
        return roomRepository.findAllByStatusNotIn(Collections.singleton(Room.RoomStatus.OCCUPIED));
    }

    public List<Room> getOccupiedRooms() {
        return roomRepository.findAllByStatus(Room.RoomStatus.OCCUPIED);
    }

    public List<Room> getAllRooms() { return roomRepository.findAll(Sort.by("roomNum"));}

    public List<Room> getAllAvailableRooms() {
        return roomRepository.findAllByStatus(Room.RoomStatus.AVAILABLE);
    }

    public Room findById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get();
        }
        return null;
    }

    public Room save(Room room){
        return roomRepository.save(room);
    }
}
