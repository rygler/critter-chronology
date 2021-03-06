package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet save(Pet pet) {
        Pet persistedPet = petRepository.save(pet);
        persistedPet.getOwner().getPets().add(persistedPet);
        customerRepository.save(persistedPet.getOwner());
        return persistedPet;
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

    public List<Pet> findByIds(List<Long> petIds) {
        return petRepository.findPetsByIdIn(petIds);
    }
}
