package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.dto.response.history.DayoffHistoryDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Dayoff;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.exception.exceptions.DayoffNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.mapper.DayoffMapper;
import com.emr.ikdemobackend.repository.DayoffRepository;
import com.emr.ikdemobackend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DayoffService {

    private DayoffMapper mapper;
    private EmployeeRepository employeeRepository;
    private DayoffRepository dayoffRepository;

    public List<DayoffDTO> getAll(){
        return dayoffRepository.findAll()
                .stream().map(mapper::toDayoffDTO)
                .collect(Collectors.toList());
    }

    public DayoffDTO getDayoff(Long id){
        return dayoffRepository.findById(id)
                .map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }

    public DayoffDTO addDayoff(RequestDayoffDTO requestDayoffDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestDayoffDTO.getEmployeeNationalId())
                .orElseThrow(()->new EmployeeNotFoundException("This employee not found in system"));
        Dayoff dayoff = mapper.toDayoffFromRequestDayoffDTO(requestDayoffDTO);
        dayoff.setEmployee(employee);
        return mapper.toDayoffDTO(dayoffRepository.save(dayoff));
    }

    public DayoffDTO updateDayoff(Long id, RequestDayoffDTO requestDayoffDTO){
        Optional<Dayoff> dayoffById = dayoffRepository.findById(id);
        Dayoff mappedDayoff = mapper.toDayoffFromRequestDayoffDTO(requestDayoffDTO);

        dayoffById.ifPresent(dayoff -> {
            dayoff.setDescription(mappedDayoff.getDescription());
            dayoff.setDateOfEnd(mappedDayoff.getDateOfEnd());
            dayoff.setDateOfReturn(mappedDayoff.getDateOfReturn());
            dayoff.setDateOfStart(mappedDayoff.getDateOfStart());
            dayoff.setDaysOfLeave(mappedDayoff.getDaysOfLeave());
            dayoff.setLeaveType(mappedDayoff.getLeaveType());
            dayoffRepository.save(dayoff);
        });

        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }

    public DayoffDTO deleteDayoff(Long id){
        Optional<Dayoff> dayoffById = dayoffRepository.findById(id);

        dayoffById.ifPresent(dayoffRepository::delete);

        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(() -> new DayoffNotFoundException("This Dayoff not found in system."));
    }

    public Set<HistoriesDTO> getDayoffHistory(){
        return dayoffRepository.findAll()
                .stream().map(mapper::toHistoryDTO)
                .collect(Collectors.toSet());
    }
}
