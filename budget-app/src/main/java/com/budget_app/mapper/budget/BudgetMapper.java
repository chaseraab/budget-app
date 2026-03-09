package com.budget_app.mapper.budget;

import com.budget_app.domain.budget.Budget;
import com.budget_app.dto.budget.*;
import com.budget_app.mapper.allocation.budget.AllocationBudgetMapper;
import com.budget_app.mapper.income.budget.IncomeBudgetMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { AllocationBudgetMapper.class, IncomeBudgetMapper.class })
public interface BudgetMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "allocations", ignore = true)
    @Mapping(target = "income", ignore = true)
    @Mapping(target = "startOfMonthBalances", ignore = true)
    @Mapping(target = "endOfMonthBalances", ignore = true)
    Budget toEntity(BudgetRequest request);

    @Mapping(target = "startOfMonthBalances", source = "startOfMonthBalances")
    @Mapping(target = "endOfMonthBalances", source = "endOfMonthBalances")
    @Mapping(target = "allocations", source = "allocations")
    @Mapping(target = "income", source = "income")
    BudgetResponse toResponse(Budget entity);
}