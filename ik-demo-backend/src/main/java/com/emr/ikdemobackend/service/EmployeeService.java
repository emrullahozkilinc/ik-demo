package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.exception.EmployeeNotFoundException;
import com.emr.ikdemobackend.mapper.EmployeeMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeMapper mapper;
    private final EmployeeRepository repository;

    public List<EmployeeDTO> getAll(){
        return repository.findAll()
                .stream().map(mapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(Long id){
        return repository.findById(id)
                .map(mapper::toEmployeeDTO)
                .orElseThrow(() -> new EmployeeNotFoundException("This Employee not found in system."));
    }

    public EmployeeDTO addEmployee(RequestEmployeeDTO requestEmployeeDTO){
        Employee employee = mapper.toEmployeeFromRequestEmployeeDTO(requestEmployeeDTO);
        return mapper.toEmployeeDTO(repository.save(employee));
    }

    public EmployeeDTO updateEmployee(Long id, RequestEmployeeDTO requestEmployeeDTO){
        Optional<Employee> employeeById = repository.findById(id);
        Employee mappedEmployee = mapper.toEmployeeFromRequestEmployeeDTO(requestEmployeeDTO);

        employeeById.ifPresent(employee -> {
            employee.setAddress(mappedEmployee.getAddress());
            employee.setEmail(mappedEmployee.getEmail());
            employee.setDepartment(mappedEmployee.getDepartment());
            employee.setBornDate(mappedEmployee.getBornDate());
            employee.setFirstName(mappedEmployee.getFirstName());
            employee.setLastName(mappedEmployee.getLastName());
            employee.setLevel(mappedEmployee.getLevel());
            employee.setNationalId(mappedEmployee.getNationalId());
            employee.setPhone(mappedEmployee.getPhone());
            employee.setPosition(mappedEmployee.getPosition());
            employee.setSalary(mappedEmployee.getSalary());
            employee.setStartDate(mappedEmployee.getStartDate());
            repository.save(employee);
        });

        return employeeById.map(mapper::toEmployeeDTO)
                .orElseThrow(() -> new EmployeeNotFoundException("This Employee not found in system."));
    }

    public EmployeeDTO deleteEmployee(Long id){
        Optional<Employee> employeeById = repository.findById(id);

        employeeById.ifPresent(repository::delete);

        return employeeById.map(mapper::toEmployeeDTO)
                .orElseThrow(() -> new EmployeeNotFoundException("This Employee not found in system."));
    }

}
