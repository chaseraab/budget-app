package com.budget_app.dto.budget;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.transaction.Transaction;

import java.time.YearMonth;
import java.util.List;

public record BudgetResponse(Long id, String name, YearMonth month, List<AllocationBudget> allocations,
                             List<Transaction> transactions, List<AccountBalance> startOfMonthBalance, List<AccountBalance> endOfMonthBalance) {
}
