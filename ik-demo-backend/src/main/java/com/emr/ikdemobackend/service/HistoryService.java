package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final DayoffService dayoffService;
    private final EmployeeService employeeService;
    private final ShiftService shiftService;
    private final SpendingService spendingService;

    public Set<HistoriesDTO> getAllHistories(){
        SortedSet<HistoriesDTO> sortedHistories = new TreeSet<>((a,b) -> -a.getUpdatedAt().compareTo(b.getUpdatedAt()));
        sortedHistories.addAll(dayoffService.getDayoffHistory());
        sortedHistories.addAll(employeeService.toHistoryDTO());
        sortedHistories.addAll(shiftService.toHistoryDTO());
        sortedHistories.addAll(spendingService.toHistoryDTO());
        return sortedHistories;
    }
}
