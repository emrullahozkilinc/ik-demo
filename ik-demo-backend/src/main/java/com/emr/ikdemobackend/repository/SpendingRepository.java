package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
}
