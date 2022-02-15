package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CustomerRepository implements ICustomerRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void persist(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer find(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer merge(Customer customer) {
        return entityManager.merge(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
    }

    @Override
    public List<Customer> all() {
        TypedQuery<Customer> query = entityManager.createNamedQuery("Customer.all", Customer.class);
        return query.getResultList();
    }
}
