package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Spending;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
    @Override
    @EntityGraph(attributePaths = "employee")
    List<Spending> findAll();
}
