//package com.budget_app.backend.implementation.account;
//
//import com.budget_app.backend.interfaces.api.ApiDeletable;
//import com.budget_app.backend.interfaces.api.ApiGetable;
//import com.budget_app.backend.interfaces.api.ApiPostable;
//import com.budget_app.backend.interfaces.api.ApiPutable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AccountService_Old implements ApiGetable<Account>, ApiPostable<Account>, ApiPutable<Account>, ApiDeletable {
//    @Autowired
//    private final AccountRepository accountRepository;
//
//    public AccountService_Old(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    public ResponseEntity<List<Account>> getAll() {
//        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
//    }
//
//    public ResponseEntity<Account> getByName(String name) {
//       try {
//           Optional<Account> retrievedAccount = accountRepository.findByName(name);
//           if (retrievedAccount.isPresent()) {
//               return new ResponseEntity<>(retrievedAccount.get(), HttpStatus.OK);
//           } else {
//               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//           }
//       } catch (IllegalArgumentException e) {
//           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//       }
//    }
//
//    public ResponseEntity<String> create(Account account) {
//        try {
//            accountRepository.save(account);
//            return new ResponseEntity<>("Account succesfully created", HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    public ResponseEntity<String> deleteById(Long id) {
//        try {
//            accountRepository.deleteById(id);
//            return new ResponseEntity<>("Account deleted", HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    public ResponseEntity<Account> update(long id, Account account) {
//        Optional<Account> searchAccount = accountRepository.findById(id);
//        if (searchAccount.isEmpty()) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//        Account existingAccount = searchAccount.get();
//        existingAccount
//                .setName(account.getName())
//                .setType(account.getType());
//        accountRepository.save(existingAccount);
//        return new ResponseEntity<>(existingAccount, HttpStatus.OK);
//    }
//
//}