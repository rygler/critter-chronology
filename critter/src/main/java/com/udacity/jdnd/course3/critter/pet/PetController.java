package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.services.CustomerService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Customer owner = customerService.find(petDTO.getOwnerId());
        Pet pet = petDTO
                    .toPet()
                    .associateOwner(owner);
        Pet persistedPet = petService.save(pet);
        return persistedPet.toDTO();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petService.find(petId).toDTO();
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService
                .findAll()
                .stream()
                .map(Pet::newDTOFromPet)
                .collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return customerService
                .find(ownerId)
                .getPets()
                .stream()
                .map(Pet::newDTOFromPet)
                .collect(Collectors.toList());
    }
}
