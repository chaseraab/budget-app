package com.budget_app.dto.accountBalance;

import java.time.LocalDate;

public record AccountBalanceResponse(Long id, LocalDate date, long accountId, float balance) {
}
