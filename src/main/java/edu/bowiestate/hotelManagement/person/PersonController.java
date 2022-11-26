package edu.bowiestate.hotelManagement.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long personId) {
        Person person = personService.findByPersonById(personId);
        if (person == null) {
            // error
            return null;
        } else {
            return person;
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Person> createNewPerson(PersonInput personInput) {
        Person person = personService.saveNewPerson(personInput);
        if (person == null) {
            // error
            return null;
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
