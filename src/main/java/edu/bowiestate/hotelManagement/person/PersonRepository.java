package edu.bowiestate.hotelManagement.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByFirstnameAndLastname(String firstname, String lastname);
}
