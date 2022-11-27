package edu.bowiestate.hotelManagement.customer;

import edu.bowiestate.hotelManagement.person.Person;
import edu.bowiestate.hotelManagement.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PersonService personService;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        } else {
            return null;
        }
    }

    public Customer findCustomerByFirstnameAndLastname(String firstname, String lastname) {
        Person person = personService.findByFirstnameAndLastname(firstname, lastname);
        Optional<Customer> customer = customerRepository.findById(person.getId());
        if(customer.isPresent()){
            return customer.get();
        } else {
            return null;
        }
    }

    public Customer createNewCustomer(CustomerInput customerInput){
        Customer customer = new Customer();
        customer.setCustId(customerInput.getPersonId());
        customer.setEmailAddress(customerInput.getEmailAddress());
        customer.setLicensePlateNum(customerInput.getLicensePlateNum());

        return customerRepository.save(customer);
    }
}
