package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.entity.Dayoff;
import com.emr.ikdemobackend.exception.DayoffNotFoundException;
import com.emr.ikdemobackend.mapper.DayoffMapper;
import com.emr.ikdemobackend.repository.DayoffRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DayoffService {

    private DayoffMapper mapper;
    private DayoffRepository repository;

    public List<DayoffDTO> getAll(){
        return repository.findAll()
                .stream().map(mapper::toDayoffDTO)
                .collect(Collectors.toList());
    }

    public DayoffDTO getDayoff(Long id){
        return repository.findById(id)
                .map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }

    public DayoffDTO addDayoff(RequestDayoffDTO requestDayoffDTO){
        Dayoff dayoff = mapper.toDayoffFromRequestDayoffDTO(requestDayoffDTO);
        return mapper.toDayoffDTO(repository.save(dayoff));
    }

    public DayoffDTO updateDayoff(Long id, RequestDayoffDTO requestDayoffDTO){
        Optional<Dayoff> dayoffById = repository.findById(id);
        Dayoff mappedDayoff = mapper.toDayoffFromRequestDayoffDTO(requestDayoffDTO);

        dayoffById.ifPresent(dayoff -> {
            dayoff.setDescription(mappedDayoff.getDescription());
            dayoff.setDateOfEnd(mappedDayoff.getDateOfEnd());
            dayoff.setDateOfReturn(mappedDayoff.getDateOfReturn());
            dayoff.setDateOfStart(mappedDayoff.getDateOfStart());
            dayoff.setDaysOfLeave(mappedDayoff.getDaysOfLeave());
            dayoff.setLeaveType(mappedDayoff.getLeaveType());
            repository.save(dayoff);
        });

        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }

    public DayoffDTO deleteDayoff(Long id){
        Optional<Dayoff> dayoffById = repository.findById(id);

        dayoffById.ifPresent(repository::delete);

        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }
}
