package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.dto.response.history.HistoriesDTO;
import com.emr.ikdemobackend.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "ShiftMapperImpl" , componentModel = "spring")
public interface ShiftMapper {
    Shift toShift(ShiftDTO shiftDTO);
    @Mapping(target = "employeeNationalId", source = "employee.nationalId")
    ShiftDTO toShiftDTO(Shift shift);
    Shift toShiftFromRequestShiftDTO(RequestShiftDTO requestShiftDTO);
    @Mapping(target = "entityName", expression = "java(shift.getClass().getSimpleName())")
    HistoriesDTO toHistoryDTO(Shift shift);
}
