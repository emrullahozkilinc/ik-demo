package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Shift;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Override
    @EntityGraph(attributePaths = "employee")
    List<Shift> findAll();

    List<Shift> findByEmployee(Employee employee);
}
