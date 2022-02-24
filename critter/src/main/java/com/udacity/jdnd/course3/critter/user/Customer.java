package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Nationalized;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NamedQuery(
        name = "Customer.all",
        query = "SELECT c FROM Customer c"
)

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    @Nationalized
    private String phoneNumber;

    @Nationalized
    private String notes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.MERGE)
    private List<Pet> pets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        if (pets == null) {
            pets = new ArrayList<>();
        }
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public CustomerDTO toDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(this, customerDTO);
        if (this.pets != null) {
            List<Long> petIds = this.pets.stream().map(Pet::getId).collect(Collectors.toList());
            customerDTO.setPetIds(petIds);
        }
        return customerDTO;
    }

    public static CustomerDTO newDTOFromCustomer(Customer customer) {
        return customer.toDTO();
    }


    public Customer associatePets(List<Pet> pets) {
        this.setPets(pets);
        return this;
    }
}
