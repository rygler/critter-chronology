package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee find(long employeeId) {
        Optional<Employee> employeeOptional =  employeeRepository.findById(employeeId);
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
}
