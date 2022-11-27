package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.employee.Employee;
import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="HOUSE_KEEP_TASK")
public class HouseKeepTask {

    @Id
    @TableGenerator(name = "HouseKeep_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "HouseKeep_Gen")
    @Column(name="TASK_ID")
    private Long taskId;

    @ManyToOne
    @JoinColumn(name="EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="ROOM_NUM", nullable = false)
    private Room room;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private TaskStatus status;

    @Column(name="DEADLINE_DATE")
    private Date deadlineDate;

    @Column(name="COMPLETION_DATE")
    private Date completionDate;

    @Column(name="CREATED_DATE")
    @CreatedDate
    private Date createdDate;

    public enum TaskStatus {
        SERVICE_REQ,
        CLEANING,
        LAUNDRY,
        COMPLETE
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
