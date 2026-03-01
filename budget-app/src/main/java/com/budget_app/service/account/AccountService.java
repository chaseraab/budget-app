package com.budget_app.service.account;

import com.budget_app.dto.account.AccountRequest;
import com.budget_app.dto.account.AccountResponse;
import com.budget_app.mapper.account.AccountMapper;
import com.budget_app.service.base.BaseService;
import com.budget_app.domain.account.Account;
import com.budget_app.repository.account.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService extends BaseService<Account, Long, AccountRequest, AccountResponse> {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;

    public AccountService(AccountRepository repository, AccountMapper mapper) {
        super (repository);
        this.accountRepository = repository;
        this.mapper = mapper;
    }

    @Override
    public Account toEntity(AccountRequest req) {
        return mapper.toEntity(req);
    }

    @Override
    public AccountResponse toResponse(Account entity) {
        return mapper.toResponse(entity);
    }

    public ResponseEntity<Account> getByName(String name) {
        Optional<Account> found = accountRepository.findByName(name);
        return found
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<AccountResponse> update(Long id, AccountRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setType(request.type());
                    existing.setIsActive(request.isActive());
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}