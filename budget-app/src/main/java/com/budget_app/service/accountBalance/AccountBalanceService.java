package com.budget_app.service.accountBalance;

import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import com.budget_app.dto.transaction.TransactionResponse;
import com.budget_app.mapper.accountBalance.AccountBalanceMapper;
import com.budget_app.search.specification.base.BaseSpecification;
import com.budget_app.service.base.BaseService;
import com.budget_app.domain.account.Account;
import com.budget_app.repository.account.AccountRepository;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.repository.accountBalance.AccountBalanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountBalanceService extends BaseService<AccountBalance, Long, AccountBalanceRequest, AccountBalanceResponse> {

    private final AccountRepository accountRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final AccountBalanceMapper mapper;

    public AccountBalanceService(AccountBalanceRepository accountBalanceRepository, AccountRepository accountRepository, AccountBalanceMapper mapper) {
        super(accountBalanceRepository);
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    private Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElse(null);
    }

    @Override
    public AccountBalance toEntity(AccountBalanceRequest req) {
        return mapper.toEntity(req);
    }

    @Override
    public AccountBalanceResponse toResponse(AccountBalance entity) {
        return  mapper.toResponse(entity);
    }

    public ResponseEntity<AccountBalanceResponse> update(Long id, AccountBalanceRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    Account account = accountRepository.findById(request.accountId())
                            .orElseThrow(() -> new RuntimeException("Account not found"));
                    existing.setDate(request.date());
                    existing.setAccount(account);
                    existing.setBalance(request.balance());

                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<AccountBalanceResponse> create(AccountBalanceRequest request) {
        Account account = getAccount(request.accountId());

        AccountBalance balance = new AccountBalance()
                .setDate(request.date())
                .setBalance(request.balance())
                .setAccount(account);

        repository.save(balance);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<AccountBalanceResponse>> search(Map<String, Object> filters) {
        return ResponseEntity.ok(accountBalanceRepository.findAll(new BaseSpecification<AccountBalance>(filters))
                .stream().map(this::toResponse)
                .toList()
        );
    }

}
