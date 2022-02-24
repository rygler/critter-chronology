package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepositoryV2;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepositoryV2 customerRepository;

    public Pet save(Pet pet) {
        Optional<Customer> ownerOptional = customerRepository.findById(pet.getOwner().getId());
        if (ownerOptional.isPresent()) {
            Customer owner = ownerOptional.get();
            if (owner.getPets() == null) {
                owner.setPets(new ArrayList<>());
            }
            pet.setOwner(owner);
            customerRepository.save(owner);
        } else {
            // TODO: Switch to accurate exception
            throw new EntityNotFoundException("Pet must have an existing owner.");
        }

        return petRepository.save(pet);
    }

    public Pet find(long petId) {
        Optional<Pet> petOptional =  petRepository.findById(petId);
        if (petOptional.isPresent()) {
            return petOptional.get();
        } else {
            throw new EntityNotFoundException("Pet with id: " + petId + " not found. Please verify that id is correct.");
        }
    }

    public Pet find(long petId, boolean shouldLoadOwner) {
        Optional<Pet> petOptional =  petRepository.findById(petId);
        if (petOptional.isPresent()) {
            return petOptional.get();
        } else {
            throw new EntityNotFoundException("Pet with id: " + petId + " not found. Please verify that id is correct.");
        }
    }


    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public void delete(Pet pet) {
        petRepository.delete(pet);
    }

    public void delete(long petId) {
        petRepository.deleteById(petId);
    }
}
