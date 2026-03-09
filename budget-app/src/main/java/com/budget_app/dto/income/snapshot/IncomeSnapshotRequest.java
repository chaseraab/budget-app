package com.budget_app.dto.income.snapshot;

public record IncomeSnapshotRequest(Long accountId, double amount, String name, boolean isActive) {
}
