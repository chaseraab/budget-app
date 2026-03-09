package com.budget_app.dto.transaction;

import java.time.LocalDate;

public record TransactionResponse(Long id, LocalDate date, Long accountId, Long allocationId, String item, String company, double amount) {
}
