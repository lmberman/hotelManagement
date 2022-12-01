package edu.bowiestate.hotelManagement.housekeep;

import edu.bowiestate.hotelManagement.housekeep.HouseKeepTask.TaskType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class HouseKeepTaskForm {

    @NotNull
    private Long taskRoomNum;

    @NotNull
    private TaskType taskType;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadlineDate;

    public Long getTaskRoomNum() {
        return taskRoomNum;
    }

    public void setTaskRoomNum(Long taskRoomNum) {
        this.taskRoomNum = taskRoomNum;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }
}
