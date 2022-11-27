package edu.bowiestate.hotelManagement.housekeep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseKeepRepository extends JpaRepository<HouseKeepTask, Long> {

    @Query(nativeQuery = true,
            value="SELECT * FROM HOUSE_KEEP_TASK WHERE STATUS <> 'COMPLETE'" )
    public List<HouseKeepTask> findAllInCompleteTasks();
}
