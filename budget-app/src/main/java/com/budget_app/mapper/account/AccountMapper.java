package com.budget_app.mapper.account;

import com.budget_app.domain.account.Account;
import com.budget_app.dto.account.AccountRequest;
import com.budget_app.dto.account.AccountResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    Account toEntity(AccountRequest request);
    AccountResponse toResponse(Account entity);
}
