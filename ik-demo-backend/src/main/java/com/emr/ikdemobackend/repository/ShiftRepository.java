package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
