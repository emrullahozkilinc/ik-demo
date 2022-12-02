package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestShiftDTO;
import com.emr.ikdemobackend.dto.response.ShiftDTO;
import com.emr.ikdemobackend.entity.Shift;
import org.mapstruct.Mapper;

@Mapper(implementationName = "ShiftMapperImpl" , componentModel = "spring")
public interface ShiftMapper {
    Shift toShift(ShiftDTO shiftDTO);
    ShiftDTO toShiftDTO(Shift shift);
    Shift toShiftFromRequestShiftDTO(RequestShiftDTO requestShiftDTO);
}
