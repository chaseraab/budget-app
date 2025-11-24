package com.budget_app.mapper.accountBalance;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountBalanceMapper {

    @Mapping(target = "account", ignore = true)
    AccountBalance toEntity(AccountBalanceRequest request);

    @Mapping(target = "accountId", source = "account.id")
    AccountBalanceResponse toResponse(AccountBalance entity);
}
