package com.budget_app.controller.accountBalance;

import com.budget_app.controller.base.BaseController;
import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import com.budget_app.service.accountBalance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accountBalances")
public class AccountBalanceController extends BaseController<Long, AccountBalanceRequest, AccountBalanceResponse> {

    @Autowired
    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        super(accountBalanceService);
        this.accountBalanceService = accountBalanceService;
    }

    @PostMapping("/new")
    public ResponseEntity<AccountBalanceResponse> create(@RequestBody AccountBalanceRequest request) {
        System.out.println("Creating new object");
        return accountBalanceService.create(request);
    }

}
