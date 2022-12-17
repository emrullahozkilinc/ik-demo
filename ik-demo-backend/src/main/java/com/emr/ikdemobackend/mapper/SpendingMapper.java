package com.emr.ikdemobackend.mapper;

import com.emr.ikdemobackend.dto.request.RequestSpendingDTO;
import com.emr.ikdemobackend.dto.response.SpendingDTO;
import com.emr.ikdemobackend.entity.Spending;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "SpendingMapperImpl" , componentModel = "spring")
public interface SpendingMapper {
    Spending toSpending(SpendingDTO spendingDTO);
    @Mapping(target = "employeeNationalId", source = "employee.nationalId")
    SpendingDTO toSpendingDTO(Spending spending);
    Spending toSpendingFromRequestSpendingDTO(RequestSpendingDTO requestSpendingDTO);

}
