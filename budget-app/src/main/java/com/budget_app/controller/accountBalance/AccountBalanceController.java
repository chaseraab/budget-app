package com.budget_app.controller.accountBalance;

import com.budget_app.controller.base.BaseController;
import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import com.budget_app.dto.transaction.TransactionResponse;
import com.budget_app.service.accountBalance.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accountBalances")
public class AccountBalanceController extends BaseController<Long, AccountBalanceRequest, AccountBalanceResponse> {

    @Autowired
    private final AccountBalanceService accountBalanceService;

    public AccountBalanceController(AccountBalanceService accountBalanceService) {
        super(accountBalanceService);
        this.accountBalanceService = accountBalanceService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountBalanceResponse>> search(@RequestBody Map<String, Object> params) {
        Map<String, Object> filters = new HashMap<>();
        params.forEach((key, value) -> {
            if (key.equals("date")) {
                filters.put(key, LocalDate.parse((CharSequence) value));
            } else {
                filters.put(key, value);
            }
        });
        return accountBalanceService.search(filters);
    }

}
