package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.base.jpa.BaseService;
import com.budget_app.backend.implementation.account.Account;
import com.budget_app.backend.implementation.account.AccountBalanceRequest;
import com.budget_app.backend.implementation.account.AccountRepository;
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
public class AccountBalanceService extends BaseService<AccountBalance, Long, AccountBalanceRequest> {

    private final AccountBalanceRepository accountBalanceRepository;
    private final AccountRepository accountRepository;

    public AccountBalanceService(AccountBalanceRepository accountBalanceRepository, AccountRepository accountRepository) {
        super(accountBalanceRepository);
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountRepository = accountRepository;
    }

    private Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElse(null);
    }

    public void updateFields(AccountBalance newBalance, AccountBalance oldBalance) {
        oldBalance.setDate(newBalance.getDate()).setAccount(newBalance.getAccount()).setBalance(newBalance.getBalance());
    }

    public ResponseEntity<String> create(AccountBalanceRequest request) {
//        Account account = accountRepository.findById(request.accountId())
//                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        Account account = getAccount(request.accountId());

        AccountBalance balance = new AccountBalance()
                .setDate(request.date())
                .setBalance(request.balance())
                .setAccount(account);

        repository.save(balance);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
