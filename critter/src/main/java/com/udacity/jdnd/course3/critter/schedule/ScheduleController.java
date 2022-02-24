package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.services.EmployeeService;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleDTO
                .toSchedule()
                .associatePets(petService.findByIds(scheduleDTO.getPetIds()))
                .associateEmployees(employeeService.findByIds(scheduleDTO.getEmployeeIds()));
        Schedule persistedSchedule = scheduleService.save(schedule);
        return persistedSchedule.toDTO();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleService
                .findAll()
                .stream()
                .map(Schedule::newDTOFromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return scheduleService
                .findScheduleByPet(petId)
                .stream()
                .map(Schedule::newDTOFromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService
                .findScheduleByEmployee(employeeId)
                .stream()
                .map(Schedule::newDTOFromSchedule)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService
                .findScheduleByCustomer(customerId)
                .stream()
                .map(Schedule::newDTOFromSchedule)
                .collect(Collectors.toList());
    }
}
