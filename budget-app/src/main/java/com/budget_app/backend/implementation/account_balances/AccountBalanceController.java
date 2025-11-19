package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.base.jpa.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accountBalances")
public class AccountBalanceController extends BaseController<AccountBalance, Long> {

    @Autowired
    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        super(accountBalanceService);
        this.accountBalanceService = accountBalanceService;
    }

}
