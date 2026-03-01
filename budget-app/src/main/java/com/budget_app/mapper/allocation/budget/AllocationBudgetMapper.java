package com.budget_app.mapper.allocation.budget;

import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.allocation.budget.AllocationBudgetResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AllocationBudgetMapper {

    AllocationBudget toEntity(AllocationBudgetRequest request);
    AllocationBudgetResponse toResponse(AllocationBudget allocation);
}
