package com.emr.ikdemobackend.service;

import com.emr.ikdemobackend.dto.response.HistoryDTO;
import com.emr.ikdemobackend.entity.enums.HistoryType;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final EntityManager entityManager;
    SortedSet<HistoryDTO> sortedHistories;

    public SortedSet<HistoryDTO> getHistory(){
        final String query =
                "select e.createdAt, e.updatedAt, 'Employee' from Employee e " +
                        "union "+
                        "select sh.createdAt, sh.updatedAt, 'Shift' from Shift sh " +
                        "union "+
                        "select d.createdAt, d.updatedAt, 'Dayoff' from Dayoff d " +
                        "union "+
                        "select sp.createdAt, sp.updatedAt, 'Spending' from Spending sp";



        sortedHistories = new TreeSet<>(Comparator.comparing(HistoryDTO::getTime));
        List<Object[]> response = entityManager.createQuery(query).getResultList();
        response.forEach(this::addResultToSet);

        return sortedHistories;
    }

    private void addResultToSet(Object[] objects){
        LocalDateTime createdAt = (LocalDateTime) objects[0];
        LocalDateTime updatedAt = (LocalDateTime) objects[1];
        String entityName = (String) objects[2];

        sortedHistories.add(new HistoryDTO(createdAt, HistoryType.CREATED, entityName));
        if (!createdAt.isEqual(updatedAt)){
            sortedHistories.add(new HistoryDTO(updatedAt, HistoryType.UPDATED, entityName));
        }
    }
}
