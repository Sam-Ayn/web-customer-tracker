package ru.springcourse.lessons.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.lessons.entities.Customer;
import ru.springcourse.lessons.service.api.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomerById(@PathVariable int customerId){
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer id not found - " + customerId);
        return customer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomerById(@PathVariable int customerId){
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) throw new CustomerNotFoundException("Customer id not found - " + customerId);
        customerService.deleteCustomer(customerId);
        return "Deleted customer with id - " + customerId;
    }
}
