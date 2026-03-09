package com.budget_app.dto.budget;

import java.time.YearMonth;

public record BudgetRequest(String name, YearMonth month) {
}
