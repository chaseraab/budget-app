package com.budget_app.backend.implementation.account;

import com.budget_app.backend.base.jpa.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController<Account, Long> {

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