package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNationalId(Long nationalId);
}
