package edu.bowiestate.hotelManagement.housekeep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseKeepRepository extends JpaRepository<HouseKeepTask, Long> {

    public List<HouseKeepTask> findAllByStatusNotOrderByCreatedDate(HouseKeepTask.TaskStatus status);
    public List<HouseKeepTask> findAllByStatusOrderByCompletionDate(HouseKeepTask.TaskStatus status);
    public List<HouseKeepTask> findByEmployeeEmployeeId(Long employeeId);

    public List<HouseKeepTask> findByEmployeeNull();
}
