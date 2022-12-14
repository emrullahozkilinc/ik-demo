package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Shift;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.ShiftNotFoundException;
import com.emr.ikdemobackend.mapper.ShiftMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.ShiftRepository;
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
public class ShiftService {
    
    private ShiftMapper mapper;
    private ShiftRepository shiftRepository;
    private EmployeeRepository employeeRepository;

    public List<ShiftDTO> getAll(){
        log.info("All Shifts listing...");
        return shiftRepository.findAll()
                .stream().map(mapper::toShiftDTO)
                .toList();
    }

    public ShiftDTO getShift(Long id){
        log.info("Shift " + id + " listing...");
        return shiftRepository.findById(id)
                .map(mapper::toShiftDTO)
                .orElseThrow(ShiftNotFoundException::new);
    }

    public ShiftDTO addShift(RequestShiftDTO requestShiftDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestShiftDTO.getEmployeeNationalId())
                .orElseThrow(()->new EmployeeNotFoundException("This employee not found in system."));
        Shift shift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);
        shift.setEmployee(employee);
        log.info("Shift adding...");
        return mapper.toShiftDTO(shiftRepository.save(shift));
    }

    public ShiftDTO updateShift(Long id, RequestShiftDTO requestShiftDTO){
        Optional<Shift> shiftById = shiftRepository.findById(id);
        Shift mappedShift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);
        log.info("Shift updating...");
        shiftById.ifPresent(shift -> {
            shift.setDescription(mappedShift.getDescription());
            shift.setDate(mappedShift.getDate());
            shift.setHours(mappedShift.getHours());
            shiftRepository.save(shift);
        });

        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(ShiftNotFoundException::new);
    }

    public ShiftDTO deleteShift(Long id){
        Optional<Shift> shiftById = shiftRepository.findById(id);

        shiftById.ifPresent(shiftRepository::delete);
        log.info("Shift deleting...");
        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(ShiftNotFoundException::new);
    }

    public Set<HistoriesDTO> toHistoryDTO(){
        log.info("Getting shifts process history...");
        return shiftRepository.findAll()
                .stream().map(mapper::toHistoryDTO)
                .collect(Collectors.toSet());
    }
}
