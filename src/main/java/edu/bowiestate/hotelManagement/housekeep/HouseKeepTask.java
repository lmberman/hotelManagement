package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.employee.Employee;
import edu.bowiestate.hotelManagement.room.Room;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="HOUSE_KEEP_TASK")
@EntityListeners(AuditingEntityListener.class)
public class HouseKeepTask {

    @Id
    @TableGenerator(name = "HouseKeep_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 5, allocationSize = 100)
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
    @Column(name="TYPE")
    private TaskType type;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private TaskStatus status;

    @Column(name="DEADLINE_DATE")
    private LocalDate deadlineDate;

    @Column(name="COMPLETION_DATE")
    private LocalDate completionDate;

    @Column(name="CREATED_DATE", nullable = false)
    private LocalDate createdDate;

    public enum TaskType {
        CLEANING,
        LAUNDRY
    }
    public enum TaskStatus {
        PENDING,
        IN_PROGRESS,
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

    public String getEmployeeName() {
        if(employee == null) {
            return "Pending Ownership";
        } else {
            return employee.getPerson().getFirstname() + " " + employee.getPerson().getLastname();
        }
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

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

}
