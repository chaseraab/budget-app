package com.budget_app.backend.implementation.account;

import com.budget_app.backend.base.jpa.BaseService;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<Account, Long> {

    public AccountService(AccountRepository repository) {super (repository);}
}