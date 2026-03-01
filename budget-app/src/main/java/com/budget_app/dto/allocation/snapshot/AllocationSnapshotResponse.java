package com.budget_app.dto.allocation.snapshot;

public record AllocationSnapshotResponse(Long id, String name, String type, double amount, boolean isActive) {
}
