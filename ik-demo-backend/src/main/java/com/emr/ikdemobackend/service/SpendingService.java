package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Spending;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.SpendingNotFoundException;
import com.emr.ikdemobackend.mapper.SpendingMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.SpendingRepository;
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
public class SpendingService {

    private SpendingMapper mapper;
    private SpendingRepository spendingRepository;
    private EmployeeRepository employeeRepository;

    public List<SpendingDTO> getAll(){
        log.info("All Spendings listing...");
        return spendingRepository.findAll()
                .stream().map(mapper::toSpendingDTO)
                .toList();
    }

    public SpendingDTO getSpending(Long id){
        log.info("Spending " + id + " listing...");
        return spendingRepository.findById(id)
                .map(mapper::toSpendingDTO)
                .orElseThrow(SpendingNotFoundException::new);
    }

    public SpendingDTO addSpending(RequestSpendingDTO requestSpendingDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestSpendingDTO.getEmployeeNationalId())
                .orElseThrow(SpendingNotFoundException::new);
        Spending spending = mapper.toSpendingFromRequestSpendingDTO(requestSpendingDTO);
        spending.setEmployee(employee);
        log.info("Spending adding...");
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
        log.info("Spending updating...");
        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(SpendingNotFoundException::new);
    }

    public SpendingDTO deleteSpending(Long id){
        Optional<Spending> spendingById = spendingRepository.findById(id);

        spendingById.ifPresent(spendingRepository::delete);

        log.info("Spending deleting...");
        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(SpendingNotFoundException::new);
    }

    public Set<HistoriesDTO> toHistoryDTO(){
        log.info("Spending history getting.");
        return spendingRepository.findAll()
                .stream().map(mapper::toHistoryDTO)
                .collect(Collectors.toSet());
    }
}
