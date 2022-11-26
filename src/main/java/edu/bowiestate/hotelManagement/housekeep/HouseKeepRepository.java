package edu.bowiestate.hotelManagement.housekeep;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseKeepRepository extends JpaRepository<HouseKeepTask, Long> {
}
