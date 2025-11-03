package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.implementation.account.Account;
import com.budget_app.backend.interfaces.api.ApiDeletable;
import com.budget_app.backend.interfaces.api.ApiPostable;
import com.budget_app.backend.interfaces.api.ApiGetable;
import com.budget_app.backend.interfaces.api.ApiPutable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public class AccountBalanceService implements ApiGetable<Account> {
    @Autowired
    private final AccountBalanceRepository accountBalanceRepository;

    public AccountBalanceService(AccountBalanceRepository accountBalanceRepository) {this.accountBalanceRepository = accountBalanceRepository;}



}
