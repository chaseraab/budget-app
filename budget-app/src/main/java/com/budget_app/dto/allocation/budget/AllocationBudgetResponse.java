package com.budget_app.dto.allocation.budget;

public record AllocationBudgetResponse(Long id, String name, String type, double amount, Long budgetId) {
}
