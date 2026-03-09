package com.budget_app.mapper.income.budget;

import com.budget_app.domain.income.budget.IncomeBudget;
import com.budget_app.dto.income.budget.IncomeBudgetRequest;
import com.budget_app.dto.income.budget.IncomeBudgetResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IncomeBudgetMapper {

    @Mapping(target = "budget", ignore = true)
    @Mapping(target = "account", ignore = true)
    IncomeBudget toEntity(IncomeBudgetRequest request);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "budgetId", source = "budget.id")
    IncomeBudgetResponse toResponse(IncomeBudget entity);
}
