package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.entity.Shift;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.ShiftNotFoundException;
import com.emr.ikdemobackend.mapper.ShiftMapper;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import com.emr.ikdemobackend.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShiftService {
    
    private ShiftMapper mapper;
    private ShiftRepository shiftRepository;
    private EmployeeRepository employeeRepository;

    public List<ShiftDTO> getAll(){
        return shiftRepository.findAll()
                .stream().map(mapper::toShiftDTO)
                .collect(Collectors.toList());
    }

    public ShiftDTO getShift(Long id){
        return shiftRepository.findById(id)
                .map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }

    public ShiftDTO addShift(RequestShiftDTO requestShiftDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestShiftDTO.getEmployeeNationalId())
                .orElseThrow(()->new EmployeeNotFoundException("This employee not found in system."));
        Shift shift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);
        shift.setEmployee(employee);
        return mapper.toShiftDTO(shiftRepository.save(shift));
    }

    public ShiftDTO updateShift(Long id, RequestShiftDTO requestShiftDTO){
        Optional<Shift> shiftById = shiftRepository.findById(id);
        Shift mappedShift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);

        shiftById.ifPresent(shift -> {
            shift.setDescription(mappedShift.getDescription());
            shift.setDate(mappedShift.getDate());
            shift.setHours(mappedShift.getHours());
            shiftRepository.save(shift);
        });

        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }

    public ShiftDTO deleteShift(Long id){
        Optional<Shift> shiftById = shiftRepository.findById(id);

        shiftById.ifPresent(shiftRepository::delete);

        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }
}
