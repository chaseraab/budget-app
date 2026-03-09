package com.budget_app.dto.income.snapshot;

public record IncomeSnapshotResponse(Long id, Long accountId, double amount, String name, boolean isActive) {
}
