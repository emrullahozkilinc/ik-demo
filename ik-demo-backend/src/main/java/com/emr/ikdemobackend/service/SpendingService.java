package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.entity.Spending;
import com.emr.ikdemobackend.exception.SpendingNotFoundException;
import com.emr.ikdemobackend.mapper.SpendingMapper;
import com.emr.ikdemobackend.repository.SpendingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpendingService {


    private SpendingMapper mapper;
    private SpendingRepository repository;

    public List<SpendingDTO> getAll(){
        return repository.findAll()
                .stream().map(mapper::toSpendingDTO)
                .collect(Collectors.toList());
    }

    public SpendingDTO getSpending(Long id){
        return repository.findById(id)
                .map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }

    public SpendingDTO addSpending(RequestSpendingDTO requestSpendingDTO){
        Spending spending = mapper.toSpendingFromRequestSpendingDTO(requestSpendingDTO);
        return mapper.toSpendingDTO(repository.save(spending));
    }

    public SpendingDTO updateSpending(Long id, RequestSpendingDTO requestSpendingDTO){
        Optional<Spending> spendingById = repository.findById(id);
        Spending mappedSpending = mapper.toSpendingFromRequestSpendingDTO(requestSpendingDTO);

        spendingById.ifPresent(spending -> {
            spending.setDescription(mappedSpending.getDescription());
            spending.setSpendingType(mappedSpending.getSpendingType());
            spending.setAmount(mappedSpending.getAmount());
            spending.setReceiptDate(mappedSpending.getReceiptDate());
            spending.setTaxRate(mappedSpending.getTaxRate());
            repository.save(spending);
        });

        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }

    public SpendingDTO deleteSpending(Long id){
        Optional<Spending> spendingById = repository.findById(id);

        spendingById.ifPresent(repository::delete);

        return spendingById.map(mapper::toSpendingDTO)
                .orElseThrow(() -> new SpendingNotFoundException("This Spending not found in system."));
    }
}
