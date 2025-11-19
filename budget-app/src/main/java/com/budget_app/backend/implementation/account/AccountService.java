package com.budget_app.backend.implementation.account;

import com.budget_app.backend.base.jpa.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService extends BaseService<Account, Long> {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository repository) {
        super (repository);
        this.accountRepository = repository;
    }

    public void updateFields(Account newAccount, Account oldAccount) {
        oldAccount.setName(newAccount.getName()).setType(newAccount.getType());
    }

    public ResponseEntity<Account> getByName(String name) {
        Optional<Account> found = accountRepository.findByName(name);
        return found
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}