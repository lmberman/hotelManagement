package edu.bowiestate.hotelManagement.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person saveNewPerson(PersonInput personInput){
        Person person = new Person();
        person.setFirstname(personInput.getFirstname());
        person.setMiddle(personInput.getMiddle());
        person.setLastname(personInput.getLastname());
        person.setTelephone(personInput.getTelephone());
        person.setAddress(personInput.getAddress());
        person.setCity(personInput.getCity());
        person.setState(personInput.getState());
        person.setZipcode(personInput.getZipcode());

        return personRepository.save(person);
    }

    public Person findByPersonById(Long id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            return null;
        }
    }

    public Person findByFirstnameAndLastname(String firstname, String lastname){
        return personRepository.findByFirstnameAndLastname(firstname, lastname);
    }
}
