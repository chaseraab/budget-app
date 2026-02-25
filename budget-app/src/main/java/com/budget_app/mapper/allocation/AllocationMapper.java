package com.budget_app.mapper.allocation;

import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.dto.allocation.AllocationRequest;
import com.budget_app.dto.allocation.AllocationResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AllocationMapper {

    AllocationBudget toEntity(AllocationRequest request);
    AllocationResponse toResponse(AllocationBudget allocation);
}
