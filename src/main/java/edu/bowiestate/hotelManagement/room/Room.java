package edu.bowiestate.hotelManagement.room;

import edu.bowiestate.hotelManagement.housekeep.HouseKeepTask;
import edu.bowiestate.hotelManagement.housekeep.HouseKeepTaskService;
import edu.bowiestate.hotelManagement.reservation.Reservation;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="ROOM")
public class Room {

    @Id
    @TableGenerator(name = "Room_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Room_Gen")
    @Column(name="ROOM_NUM")
    private long roomNum;

    @Enumerated(EnumType.STRING)
    @Column(name="TYPE")
    private RoomType type;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private RoomStatus status;

    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "room")
    private Set<HouseKeepTask> houseKeepTasks;

    public long getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(long roomNum) {
        this.roomNum = roomNum;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public enum RoomType{
        SINGLE("Single"),
        DOUBLE("Double"),
        SUITE("Suite");

        private String type;

        RoomType(String type){
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public enum RoomStatus{
        AVAILABLE("Available"),
        OCCUPIED("Occupied"),
        SERVICE_REQ("Service Required"),
        SERVICE_IN_PROCESS("Service in Process");

        private String value;

        RoomStatus(String value){
            this.value = value;
        }


    }


}
