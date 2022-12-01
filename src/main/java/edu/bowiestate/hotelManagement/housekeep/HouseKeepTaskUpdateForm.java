package edu.bowiestate.hotelManagement.housekeep;

import javax.validation.constraints.NotNull;

public class HouseKeepTaskUpdateForm {
    @NotNull
    private HouseKeepTask.TaskType taskType;

    @NotNull
    private HouseKeepTask.TaskStatus status;

    public HouseKeepTask.TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(HouseKeepTask.TaskType taskType) {
        this.taskType = taskType;
    }

    public HouseKeepTask.TaskStatus getStatus() {
        return status;
    }

    public void setStatus(HouseKeepTask.TaskStatus status) {
        this.status = status;
    }
}
