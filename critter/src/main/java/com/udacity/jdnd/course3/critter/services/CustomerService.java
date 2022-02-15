package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepositoryV2;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerRepositoryV2 customerRepositoryV2;


    public Customer save(Customer customer) {
//        setOwnerOnPets(customer);
//        customerRepository.persist(customer);
//        long customerId = customer.getId();
//        return customerRepository.find(customerId);
        setOwnerOnPets(customer);
        return customerRepositoryV2.save(customer);
    }

    public List<Customer> findAll() {
//        return customerRepository.all();
        return customerRepositoryV2.findAll();
    }

    private void setOwnerOnPets(Customer customer) {
        if (customer.getPets() != null) {
            customer.getPets().forEach(pet -> pet.setOwner(customer));
        }
    }


}
