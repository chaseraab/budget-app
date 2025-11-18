//package com.budget_app.backend.implementation.account_balances;
//
//import com.budget_app.backend.interfaces.api.ApiDeletable;
//import com.budget_app.backend.interfaces.api.ApiPostable;
//import com.budget_app.backend.interfaces.api.ApiGetable;
//import com.budget_app.backend.interfaces.api.ApiPutable;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//public class AccountBalanceService implements ApiGetable<AccountBalance> {
//    @Autowired
//    private final AccountBalanceRepository accountBalanceRepository;
//
//    public AccountBalanceService(AccountBalanceRepository accountBalanceRepository) {this.accountBalanceRepository = accountBalanceRepository;}
//
//    public ResponseEntity<List<AccountBalance>> getAll() {
//        return new ResponseEntity<>(accountBalanceRepository.findAll(), HttpStatus.OK);
//    }
//
//    public ResponseEntity<AccountBalance> getById(long id) {
//        try {
//            Optional<AccountBalance> retrievedBalance = accountBalanceRepository.findById(id);
//            if (retrievedBalance.isPresent()) {
//                return new ResponseEntity<>(retrievedBalance.get(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//}
