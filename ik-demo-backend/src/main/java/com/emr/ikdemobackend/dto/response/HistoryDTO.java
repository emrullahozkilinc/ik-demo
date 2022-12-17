package com.emr.ikdemobackend.dto.response;

import com.emr.ikdemobackend.entity.enums.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class HistoryDTO {
    private final LocalDateTime time;
    private final HistoryType historyType;
    private final String entityName;
}
