package com.budget_app.dto.budget;

import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import com.budget_app.dto.allocation.budget.AllocationBudgetResponse;
import com.budget_app.dto.income.budget.IncomeBudgetResponse;

import java.time.YearMonth;
import java.util.List;

public record BudgetResponse(Long id, String name, YearMonth month, List<IncomeBudgetResponse> income, List<AllocationBudgetResponse> allocations,
                             List<AccountBalanceResponse> startOfMonthBalances, List<AccountBalanceResponse> endOfMonthBalances) {
}
