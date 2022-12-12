package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Dayoff;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayoffRepository extends JpaRepository<Dayoff, Long> {
    @Override
    @EntityGraph(attributePaths = "employee")
    List<Dayoff> findAll();
}
