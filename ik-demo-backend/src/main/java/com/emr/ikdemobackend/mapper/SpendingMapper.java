package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.entity.Spending;
import org.mapstruct.Mapper;

@Mapper(implementationName = "SpendingMapperImpl" , componentModel = "spring")
public interface SpendingMapper {
    Spending toSpending(SpendingDTO spendingDTO);
    SpendingDTO toSpendingDTO(Spending spending);
    Spending toSpendingFromRequestSpendingDTO(RequestSpendingDTO requestSpendingDTO);
}
