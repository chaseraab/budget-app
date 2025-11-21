package com.budget_app.mapper.accountBalance;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountBalanceMapper {
    AccountBalance toEntity(AccountBalanceRequest request);
    AccountBalanceResponse toResponse(AccountBalance entity);
}
