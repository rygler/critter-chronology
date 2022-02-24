package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepositoryV2;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerRepositoryV2 customerRepositoryV2;

    public Customer save(Customer customer) {
        return customerRepositoryV2.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepositoryV2.findAll();
    }

    public Customer find(long customerId) {
        Optional<Customer> customerOptional = customerRepositoryV2.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new EntityNotFoundException("Customer with id: " + customerId + " not found. Please verify that id is correct.");
        }
    }


}
