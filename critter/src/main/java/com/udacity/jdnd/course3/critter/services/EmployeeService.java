package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee find(long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new EntityNotFoundException("Employee with id: " + employeeId + " not found. Please verify that id is correct.");
        }
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    public void delete(long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Employee employee) {
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        return employeeRepository
                .findEmployeesByDaysAvailableContaining(date.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

    public Set<Employee> findByIds(List<Long> employeeIds) {
        return employeeRepository.findEmployeesByIdIn(employeeIds);
    }
}
