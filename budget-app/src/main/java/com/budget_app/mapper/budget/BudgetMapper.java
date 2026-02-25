package com.budget_app.mapper.budget;

import com.budget_app.domain.budget.Budget;
import com.budget_app.dto.budget.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    @Mapping(target = "allocation", ignore = true)
    @Mapping(target = "transaction", ignore = true)
    @Mapping(target = "accountBalance", ignore = true)
    Budget toEntity(BudgetRequest request);

    @Mapping(source = "allocation.id", target = "allocationId")
    @Mapping(source = "transaction.id", target = "transactionId")
    @Mapping(source = "account_balance.id", target = "accountBalanceId")
    BudgetResponse toResponse(Budget entity);
}
