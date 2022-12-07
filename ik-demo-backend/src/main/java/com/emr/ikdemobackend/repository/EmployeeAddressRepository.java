package com.emr.ikdemobackend.repository;

import com.emr.ikdemobackend.entity.EmployeeAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAddressRepository extends JpaRepository<EmployeeAddress, Long> {
}