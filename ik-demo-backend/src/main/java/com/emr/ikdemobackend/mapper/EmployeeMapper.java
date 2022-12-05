package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import com.emr.ikdemobackend.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "EmployeeMapperImpl" , componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);
    @Mapping(source = "address", target = "address")
    EmployeeDTO toEmployeeDTO(Employee employee);
    Employee toEmployeeFromRequestEmployeeDTO(RequestEmployeeDTO requestEmployeeDTO);
}
