package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;

import java.util.List;

public interface ICustomerRepository {
    void persist(Customer customer);
    Customer find(Long id);
    Customer merge(Customer customer);
    void delete(Long id);
    List<Customer> all();
}
