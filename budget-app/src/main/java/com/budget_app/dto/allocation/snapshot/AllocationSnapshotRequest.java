package com.budget_app.dto.allocation.snapshot;

public record AllocationSnapshotRequest(String name, String type, double amount, boolean isActive) {
}
