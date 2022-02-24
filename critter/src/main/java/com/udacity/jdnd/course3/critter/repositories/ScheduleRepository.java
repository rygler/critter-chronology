package com.udacity.jdnd.course3.critter.repositories;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findSchedulesByPetsContaining(Pet pet);
    List<Schedule> findSchedulesByPetsIn(List<Pet> pet);
    List<Schedule> findSchedulesByEmployeesContaining(Employee employee);

}
