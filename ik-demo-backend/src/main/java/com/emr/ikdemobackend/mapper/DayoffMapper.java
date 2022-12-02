package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestDayoffDTO;
import com.emr.ikdemobackend.dto.response.DayoffDTO;
import com.emr.ikdemobackend.entity.Dayoff;
import org.mapstruct.Mapper;

@Mapper(implementationName = "DayoffMapperImpl" , componentModel = "spring")
public interface DayoffMapper {
    Dayoff toDayoff(DayoffDTO dayoffDTO);

    DayoffDTO toDayoffDTO(Dayoff dayoff);

    Dayoff toDayoffFromRequestDayoffDTO(RequestDayoffDTO requestDayoffDTO);
}
