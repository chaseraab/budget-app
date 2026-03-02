package com.budget_app.dto.income.budget;

import com.budget_app.domain.budget.Budget;

public record IncomeBudgetResponse(Long id, Long accountId, double amount, String name, Budget budget) {
}
