package com.budget_app.backend.implementation.account;

import com.budget_app.backend.implementation.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return accountService.getAll();
    }

//    @GetMapping(path = "/name/{name}")
//    public ResponseEntity<Account> getAccountByName(@PathVariable("name") String name) {
//        return accountService.getByName(name);
//    }
//
//    @PostMapping("/new/")
//    public ResponseEntity<String> addAccount(@RequestBody Account account) {
//        return accountService.create(account);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
//        return accountService.deleteById(id);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Account> updateById(@PathVariable Long id, @RequestBody Account account) {
//        return accountService.update(id, account);
//    }
}