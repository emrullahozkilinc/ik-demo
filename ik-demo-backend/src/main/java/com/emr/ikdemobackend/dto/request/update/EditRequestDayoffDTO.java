package com.emr.ikdemobackend.dto.request.update;

import com.emr.ikdemobackend.entity.enums.LeaveType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class EditRequestDayoffDTO {

    private final LeaveType leaveType;
    private final int daysOfLeave;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime dateOfStart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime dateOfEnd;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime dateOfReturn;
    private final String description;
}
