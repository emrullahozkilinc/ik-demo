package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.entity.Shift;
import com.emr.ikdemobackend.exception.ShiftNotFoundException;
import com.emr.ikdemobackend.mapper.ShiftMapper;
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
    private ShiftRepository repository;

    public List<ShiftDTO> getAll(){
        return repository.findAll()
                .stream().map(mapper::toShiftDTO)
                .collect(Collectors.toList());
    }

    public ShiftDTO getShift(Long id){
        return repository.findById(id)
                .map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }

    public ShiftDTO addShift(RequestShiftDTO requestShiftDTO){
        Shift shift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);
        return mapper.toShiftDTO(repository.save(shift));
    }

    public ShiftDTO updateShift(Long id, RequestShiftDTO requestShiftDTO){
        Optional<Shift> shiftById = repository.findById(id);
        Shift mappedShift = mapper.toShiftFromRequestShiftDTO(requestShiftDTO);

        shiftById.ifPresent(shift -> {
            shift.setDescription(mappedShift.getDescription());
            shift.setDate(mappedShift.getDate());
            shift.setHours(mappedShift.getHours());
            repository.save(shift);
        });

        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }

    public ShiftDTO deleteShift(Long id){
        Optional<Shift> shiftById = repository.findById(id);

        shiftById.ifPresent(repository::delete);

        return shiftById.map(mapper::toShiftDTO)
                .orElseThrow(() -> new ShiftNotFoundException("This Shift not found in system."));
    }
}
