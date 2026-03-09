package com.budget_app.mapper.allocation.snapshot;

import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotRequest;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AllocationSnapshotMapper {

    AllocationSnapshot toEntity(AllocationSnapshotRequest request);
    AllocationSnapshotResponse toResponse(AllocationSnapshot allocation);
}
