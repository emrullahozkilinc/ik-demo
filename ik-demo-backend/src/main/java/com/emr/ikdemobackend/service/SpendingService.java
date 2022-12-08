package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Spending;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.SpendingNotFoundException;
import com.emr.ikdemobackend.mapper.SpendingMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.SpendingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpendingService {


    private SpendingMapper mapper;
    private SpendingRepository spendingRepository;
    private EmployeeRepository employeeRepository;

    public List<SpendingDTO> getAll(){
        return spendingRepository.findAll()
                .stream().map(mapper::toSpendingDTO)
                .collect(Collectors.toList());
    }

    public SpendingDTO getSpending(Long id){
        return spendingRepository.findById(id)
                .map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }

    public SpendingDTO addSpending(RequestSpendingDTO requestSpendingDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestSpendingDTO.getEmployeeNationalId())
                .orElseThrow(()-> new EmployeeNotFoundException("This employee not found in sysytem."));
        Spending spending = mapper.toSpendingFromRequestSpendingDTO(requestSpendingDTO);
        System.err.println(employee.getFirstName());
        spending.setEmployee(employee);
        return mapper.toSpendingDTO(spendingRepository.save(spending));
    }

    public SpendingDTO updateSpending(Long id, RequestSpendingDTO requestSpendingDTO){
        Optional<Spending> spendingById = spendingRepository.findById(id);
        Spending mappedSpending = mapper.toSpendingFromRequestSpendingDTO(requestSpendingDTO);

        spendingById.ifPresent(spending -> {
            spending.setDescription(mappedSpending.getDescription());
            spending.setSpendingType(mappedSpending.getSpendingType());
            spending.setAmount(mappedSpending.getAmount());
            spending.setReceiptDate(mappedSpending.getReceiptDate());
            spending.setTaxRate(mappedSpending.getTaxRate());
            spendingRepository.save(spending);
        });

        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }

    public SpendingDTO deleteSpending(Long id){
        Optional<Spending> spendingById = spendingRepository.findById(id);

        spendingById.ifPresent(spendingRepository::delete);

        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }
}
