//package com.budget_app.backend.implementation.account_balances;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/accountBalances")
//public class AccountBalanceController {
//
//    @Autowired
//    private final AccountBalanceService accountBalanceService;
//
//    public AccountBalanceController(AccountBalanceService accountBalanceService) {this.accountBalanceService = accountBalanceService;}
//
//    @GetMapping("/all")
//    public ResponseEntity<List<AccountBalance>> getAllAccountBalances() {return accountBalanceService.getAll();}
//
//    @GetMapping("/id/{id}}")
//    public ResponseEntity<AccountBalance> getAccountBalanceById (long id) {return accountBalanceService.getById(id);}
//}
