package com.budget_app.dto.income.budget;

import com.budget_app.domain.budget.Budget;

public record IncomeBudgetRequest(Long accountId, double amount, String name, Long budgetId) {
}
