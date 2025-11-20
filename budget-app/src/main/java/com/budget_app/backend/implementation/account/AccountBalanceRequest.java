package com.budget_app.backend.implementation.account;

import java.time.LocalDate;

public record AccountBalanceRequest(LocalDate date, Long accountId, float balance) {

}
