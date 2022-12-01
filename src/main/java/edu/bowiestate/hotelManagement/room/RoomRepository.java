package edu.bowiestate.hotelManagement.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    public List<Room> findAllByStatusNotIn(Collection<Room.RoomStatus> status);

    public List<Room> findAllByStatus(Room.RoomStatus status);

    @Query(nativeQuery = true, value = "SELECT r.* " +
            "FROM ROOM r " +
            "WHERE r.ROOM_NUM " +
            "NOT IN (Select res.ROOM_NUM " +
            "FROM RESERVATION res WHERE :currentDay BETWEEN res.START_DATE and res.END_DATE " +
            "AND res.STATUS IN ('OPEN','IN_PROGRESS'))" +
            "AND r.STATUS = 'AVAILABLE'")
    List<Room> findAllAvailableRooms(@Param("currentDay") LocalDate currentDay);

    @Query(nativeQuery = true, value = "SELECT r.* " +
            "FROM ROOM r " +
            "WHERE r.ROOM_NUM " +
            "NOT IN (SELECT hk.ROOM_NUM " +
            "FROM HOUSE_KEEP_TASK hk WHERE hk.status IN ('PENDING','IN_PROGRESS'))")
    public List<Room> findRoomsWithNoPendingOrInProgressTasks();
}
