package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.base.jpa.BaseService;
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

@Service
public class AccountBalanceService extends BaseService<AccountBalance, Long> {

    private final AccountBalanceRepository accountBalanceRepository;

    public AccountBalanceService(AccountBalanceRepository accountBalanceRepository) {
        super(accountBalanceRepository);
        this.accountBalanceRepository = accountBalanceRepository;
    }

    public void updateFields(AccountBalance newBalance, AccountBalance oldBalance) {
        oldBalance.setDate(newBalance.getDate()).setAccount(newBalance.getAccount()).setBalance(newBalance.getBalance());
    }

}
