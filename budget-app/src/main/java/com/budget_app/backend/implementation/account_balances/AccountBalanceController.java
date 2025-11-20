package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.base.jpa.BaseController;
import com.budget_app.backend.implementation.account.AccountBalanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accountBalances")
public class AccountBalanceController extends BaseController<AccountBalance, ResponseEntity> {

    @Autowired
    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        super(accountBalanceService);
        this.accountBalanceService = accountBalanceService;
    }

    @PostMapping("/new/request")
    public ResponseEntity<String> create(@RequestBody AccountBalanceRequest request) {
        System.out.println("Creating new object");
        return accountBalanceService.create(request);
    }

}
