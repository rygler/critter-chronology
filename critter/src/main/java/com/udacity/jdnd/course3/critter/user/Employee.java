package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<DayOfWeek> daysAvailable;

    public Employee(Long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee() {
    }


    public EmployeeDTO toDto() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(this, employeeDTO);
        return employeeDTO;
    }

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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public EmployeeDTO toDTO() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(this, employeeDTO);
        return employeeDTO;
    }

    public static EmployeeDTO newDTOFromEmployee(Employee employee) {
        return employee.toDto();
    }
}
