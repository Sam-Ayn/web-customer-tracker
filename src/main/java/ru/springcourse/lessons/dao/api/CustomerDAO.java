package ru.springcourse.lessons.dao.api;

import ru.springcourse.lessons.entities.Customer;

import java.util.List;

public interface CustomerDAO {

    void saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    List<Customer> getCustomers();

    void deleteCustomer(int customerId);

}
