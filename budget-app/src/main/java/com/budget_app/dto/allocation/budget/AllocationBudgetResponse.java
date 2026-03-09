package com.budget_app.dto.allocation.budget;

import com.budget_app.dto.transaction.TransactionResponse;

import java.util.List;

public record AllocationBudgetResponse(Long id, String name, String type, double amount, Long budgetId, List<TransactionResponse> transactions) {
}
