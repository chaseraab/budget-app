package com.budget_app.mapper.income.snapshot;

import com.budget_app.domain.income.snapshot.IncomeSnapshot;
import com.budget_app.dto.income.snapshot.IncomeSnapshotRequest;
import com.budget_app.dto.income.snapshot.IncomeSnapshotResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IncomeSnapshotMapper {

    @Mapping(target = "account", ignore = true)
    IncomeSnapshot toEntity(IncomeSnapshotRequest request);

    @Mapping(target = "budgetId", source = "budget.id")
    IncomeSnapshotResponse toResponse(IncomeSnapshot entity);
}
