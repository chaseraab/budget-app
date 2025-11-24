package com.budget_app.mapper.allocation;

import com.budget_app.domain.allocation.Allocation;
import com.budget_app.dto.allocation.AllocationRequest;
import com.budget_app.dto.allocation.AllocationResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AllocationMapper {

    Allocation toEntity(AllocationRequest request);
    AllocationResponse toResponse(Allocation allocation);
}
