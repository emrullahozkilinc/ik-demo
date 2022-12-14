package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Dayoff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "DayoffMapperImpl" , componentModel = "spring")
public interface DayoffMapper {
    Dayoff toDayoff(DayoffDTO dayoffDTO);

    @Mapping(target = "employeeNationalId", source = "employee.nationalId")
    @Mapping(target = "leaveType", expression = "java(dayoff.getLeaveType().getName())")
    DayoffDTO toDayoffDTO(Dayoff dayoff);

    @Mapping(target = "leaveType", expression = "java(LeaveType.getByName(requestDayoffDTO.getLeaveType()))")
    Dayoff toDayoffFromRequestDayoffDTO(RequestDayoffDTO requestDayoffDTO);

    @Mapping(target = "entityName", expression = "java(dayoff.getClass().getSimpleName())")
    HistoriesDTO toHistoryDTO(Dayoff dayoff);
}
