package com.budget_app.controller.account;

import com.budget_app.controller.base.BaseController;
import com.budget_app.domain.account.Account;
import com.budget_app.dto.account.AccountRequest;
import com.budget_app.dto.account.AccountResponse;
import com.budget_app.service.account.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController<Long, AccountRequest, AccountResponse> {

    private final AccountService accountService;

    public AccountController(AccountService service) {
        super(service);
        this.accountService = service;
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Account> getAccountByName(@PathVariable("name") String name) {
        return accountService.getByName(name);
    }

}