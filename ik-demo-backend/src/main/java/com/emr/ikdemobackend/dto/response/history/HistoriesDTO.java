package com.emr.ikdemobackend.dto.response.history;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoriesDTO {
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String entityName;
}