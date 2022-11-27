package edu.bowiestate.hotelManagement;

import edu.bowiestate.hotelManagement.person.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void saveNewPerson(){
        PersonInput input = new PersonInput();
        input.setFirstname("Jim");
        input.setMiddle('M');
        input.setLastname("Bean");
        input.setTelephone("851-234-2394");
        input.setAddress("1234 Markel Ave");
        input.setCity("San Franscisco");
        input.setState("CA");
        input.setZipcode("90218");

        Person newPerson = personService.saveNewPerson(input);

        assertThat(Long.valueOf(newPerson.getId())).isNotNull();
    }

    @Test
    public void unableToFindPersonById() {
        assertThat(personService.findByPersonById(Long.valueOf("1L"))).isNull();
    }

    @Test
    public void findPersonById() {
        PersonInput input = new PersonInput();
        input.setFirstname("Jim");
        input.setMiddle('M');
        input.setLastname("Bean");
        input.setTelephone("851-234-2394");
        input.setAddress("1234 Markel Ave");
        input.setCity("San Franscisco");
        input.setState("CA");
        input.setZipcode("90218");

        Person newPerson = personService.saveNewPerson(input);
        assertThat(personService.findByPersonById(newPerson.getId())).isNotNull();
    }


}
