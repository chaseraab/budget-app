package com.budget_app.dto.accountBalance;

import java.time.LocalDate;

public record AccountBalanceRequest(LocalDate date, Long accountId, float balance, Long budgetId) {

}
