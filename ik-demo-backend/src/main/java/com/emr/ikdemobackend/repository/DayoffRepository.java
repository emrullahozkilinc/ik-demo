package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Dayoff;
import com.emr.ikdemobackend.entity.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayoffRepository extends JpaRepository<Dayoff, Long> {
    @Override
    @EntityGraph(attributePaths = "employee")
    List<Dayoff> findAll();

    List<Dayoff> findByEmployee(Employee employee);

    @Query("select d from Dayoff d where d.employee.nationalId = ?1")
    List<Dayoff> findByNationalId(Long nationalId);
}
