package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Dayoff;
import com.emr.ikdemobackend.entity.Employee;
import com.emr.ikdemobackend.exception.exceptions.DayoffNotFoundException;
import com.emr.ikdemobackend.exception.exceptions.EmployeeNotFoundException;
import com.emr.ikdemobackend.mapper.DayoffMapper;
import com.emr.ikdemobackend.repository.DayoffRepository;
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
public class DayoffService {

    private DayoffMapper mapper;
    private EmployeeRepository employeeRepository;
    private DayoffRepository dayoffRepository;

    public List<DayoffDTO> getAll(){
        log.info("All Dayoffs listing...");
        return dayoffRepository.findAll()
                .stream().map(mapper::toDayoffDTO)
                .toList();
    }

    public DayoffDTO getDayoff(Long id){
        log.info("Dayoff " + id + " listing...");
        return dayoffRepository.findById(id)
                .map(mapper::toDayoffDTO)
                .orElseThrow(DayoffNotFoundException::new);
    }

    public DayoffDTO addDayoff(RequestDayoffDTO requestDayoffDTO){
        Employee employee = employeeRepository
                .findByNationalId(requestDayoffDTO.getEmployeeNationalId())
                .orElseThrow(DayoffNotFoundException::new);
        Dayoff dayoff = mapper.toDayoffFromRequestDayoffDTO(requestDayoffDTO);
        dayoff.setEmployee(employee);
        log.info("Dayoff adding...");
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
        log.info("Dayoff updating...");
        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(DayoffNotFoundException::new);
    }

    public DayoffDTO deleteDayoff(Long id){
        Optional<Dayoff> dayoffById = dayoffRepository.findById(id);

        dayoffById.ifPresent(dayoffRepository::delete);

        log.info("Dayoff deleting...");
        return dayoffById.map(mapper::toDayoffDTO)
                .orElseThrow(DayoffNotFoundException::new);
    }

    public Set<HistoriesDTO> getDayoffHistory(){
        log.info("Dayoff history getting.");
        return dayoffRepository.findAll()
                .stream().map(mapper::toHistoryDTO)
                .collect(Collectors.toSet());
    }
}
