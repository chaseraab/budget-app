package com.budget_app.dto.allocation;

public record AllocationRequest(String name, String type, double amount, boolean isTemp, boolean isExpected) {
}
