package com.budget_app.dto.income;

public record IncomeRequest(Long accountId, double amount, String name, boolean isRecurring) {
}
