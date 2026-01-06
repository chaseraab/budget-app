package com.budget_app.mapper.income;

import com.budget_app.domain.income.Income;
import com.budget_app.dto.income.IncomeRequest;
import com.budget_app.dto.income.IncomeResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IncomeMapper {

    @Mapping(target = "account", ignore = true)
    Income toEntity(IncomeRequest request);

    @Mapping(target = "accountId", source = "account.id")
    IncomeResponse toResponse(Income entity);
}
