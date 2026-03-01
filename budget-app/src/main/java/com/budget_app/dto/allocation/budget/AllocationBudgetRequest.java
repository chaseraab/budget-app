package com.budget_app.dto.allocation.budget;

public record AllocationBudgetRequest(String name, String type, double amount, Long budgetId) {
}
