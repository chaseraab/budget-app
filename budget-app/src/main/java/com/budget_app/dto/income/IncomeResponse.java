package com.budget_app.dto.income;

public record IncomeResponse(Long id, Long accountId, double amount, String name, boolean isRecurring) {
}
