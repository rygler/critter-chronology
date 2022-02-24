package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<EmployeeSkill> activities;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employees;

    public Schedule() {
    }

    public Schedule(long id, List<Pet> pets, LocalDate date, Set<EmployeeSkill> activities, Set<Employee> employees) {
        this.id = id;
        this.pets = pets;
        this.date = date;
        this.activities = activities;
        this.employees = employees;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        if (activities == null) {
            activities = new HashSet<>();
        }
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public Set<Employee> getEmployees() {
        if (employees == null) {
            employees = new HashSet<>();
        }
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public ScheduleDTO toDTO() {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(this, scheduleDTO);
        scheduleDTO.setPetIds(getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        scheduleDTO.setEmployeeIds(getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        return scheduleDTO;
    }

    public static ScheduleDTO newDTOFromSchedule(Schedule schedule) {
        return schedule.toDTO();
    }

    public Schedule associatePets(List<Pet> pets) {
        this.setPets(pets);
        return this;
    }

    public Schedule associateEmployees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }
}
