package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestEmployeeDTO;
import com.emr.ikdemobackend.dto.response.EmployeeDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.EmployeeAddress;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.mapper.EmployeeMapper;
import com.emr.ikdemobackend.repository.EmployeeAddressRepository;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService {


    private EmployeeMapper mapper;
    private EmployeeRepository employeeRepository;
    private EmployeeAddressRepository addressRepository;


    public List<EmployeeDTO> getAll(){
        log.info("All Employees listing...");
        return employeeRepository.findAll()
                .stream().map(mapper::toEmployeeDTO)
                .toList();
    }

    public EmployeeDTO getEmployee(Long id){
        log.info("Employee " + id + " listing...");
        return employeeRepository.findById(id)
                .map(mapper::toEmployeeDTO)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public EmployeeDTO addEmployee(RequestEmployeeDTO requestEmployeeDTO){
        Employee employee = mapper.toEmployeeFromRequestEmployeeDTO(requestEmployeeDTO);
        EmployeeDTO mappedEmployee = mapper.toEmployeeDTO(employee);
        EmployeeAddress address = addressRepository.save(
                mapper.toEmployeeAddress(mappedEmployee.getAddress())
        );
        employee.setAddress(address);
        log.info("Employee adding...");
        return mapper.toEmployeeDTO(employeeRepository.save(employee));
    }

    public EmployeeDTO updateEmployee(Long nationalId, RequestEmployeeDTO requestEmployeeDTO){
        Optional<Employee> employeeByNatId = employeeRepository.findByNationalId(nationalId);
        Employee mappedEmployee = mapper.toEmployeeFromRequestEmployeeDTO(requestEmployeeDTO);
        setEmployeeAndSave(employeeByNatId, mappedEmployee);
        log.info("Employee updating...");
        return employeeByNatId.map(mapper::toEmployeeDTO)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public EmployeeDTO deleteEmployee(Long nationalId){
        Optional<Employee> employeeById = employeeRepository.findByNationalId(nationalId);

        employeeById.ifPresent(employeeRepository::delete);
        log.info("Employee deleting...");
        return employeeById.map(mapper::toEmployeeDTO)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    private void setEmployeeAndSave(Optional<Employee> optionalEmployee, Employee employeeToSave){
        optionalEmployee.ifPresent(employee -> {
            employee.setEmail(employeeToSave.getEmail());
            employee.setDepartment(employeeToSave.getDepartment());
            employee.setBornDate(employeeToSave.getBornDate());
            employee.setFirstName(employeeToSave.getFirstName());
            employee.setLastName(employeeToSave.getLastName());
            employee.setLevel(employeeToSave.getLevel());
            employee.setNationalId(employeeToSave.getNationalId());
            employee.setPhone(employeeToSave.getPhone());
            employee.setPosition(employeeToSave.getPosition());
            employee.setSalary(employeeToSave.getSalary());
            employee.setStartDate(employeeToSave.getStartDate());
            employee.getAddress().setAddress(employeeToSave.getAddress().getAddress());
            employee.getAddress().setCity(employeeToSave.getAddress().getCity());
            employee.getAddress().setCountry(employeeToSave.getAddress().getCountry());
            employee.getAddress().setPostalCode(employeeToSave.getAddress().getPostalCode());

            employeeRepository.save(employee);
        });
    }

    public Set<HistoriesDTO> toHistoryDTO(){
        log.info("Getting employees process history...");
        return employeeRepository.findAll()
                .stream().map(mapper::toHistoryDTO)
                .collect(Collectors.toSet());
    }
}
