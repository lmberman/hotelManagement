package edu.bowiestate.hotelManagement.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/customer")
    public Customer getCustomerByName(@RequestParam String firstname, @RequestParam String lastname) {
        return customerService.findCustomerByFirstnameAndLastname(firstname, lastname);
    }

    @PostMapping("/customer/{id}")
    public Customer createNewCustomer(@RequestParam CustomerInput customerInput) {
        return customerService.createNewCustomer(customerInput);
    }
}
