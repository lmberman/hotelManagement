package edu.bowiestate.hotelManagement.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
}
