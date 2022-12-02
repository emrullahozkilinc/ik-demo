package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Dayoff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayoffRepository extends JpaRepository<Dayoff, Long> {
}
