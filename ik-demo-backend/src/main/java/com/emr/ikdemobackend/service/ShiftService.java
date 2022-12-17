package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Shift;
import com.emr.ikdemobackend.exception.exceptions.DateConflictException;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.ShiftNotFoundException;
import com.emr.ikdemobackend.mapper.ShiftMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
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

        if (isDatesConflicts(employee, requestShiftDTO.getDate()))
            throw new DateConflictException();

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
            setShift(shift, mappedShift);
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

    private boolean isDatesConflicts(Employee employee, LocalDateTime date){
        List<Shift> shifts = shiftRepository.findByEmployee(employee);

        if (!shifts.isEmpty())
            return shifts.stream().anyMatch(isGivenDateBetweenEmployeeDate(date));
        else
            return false;
    }

    private Predicate<Shift> isGivenDateBetweenEmployeeDate(LocalDateTime date){
        return shift -> (date.isAfter(shift.getDate()) && date.isBefore(shift.getDate()));
    }

    private void setShift(Shift shift, Shift mappedShift){
        shift.setDescription(mappedShift.getDescription());
        shift.setDate(mappedShift.getDate());
        shift.setHours(mappedShift.getHours());
    }
}
