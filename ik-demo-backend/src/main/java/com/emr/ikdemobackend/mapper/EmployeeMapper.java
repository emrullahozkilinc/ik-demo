package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import com.emr.ikdemobackend.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(implementationName = "EmployeeMapperImpl" , componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployeeFromRequestEmployeeDTO(RequestEmployeeDTO requestEmployeeDTO);
}
