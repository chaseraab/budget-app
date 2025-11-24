package com.budget_app.dto.allocation;

public record AllocationResponse(Long id, String name, String type, double amount, boolean isTemp, boolean isExpected) {
}
