package com.budget_app.service.accountBalance;

import com.budget_app.dto.accountBalance.AccountBalanceResponse;
import com.budget_app.mapper.accountBalance.AccountBalanceMapper;
import com.budget_app.service.base.BaseService;
import com.budget_app.domain.account.Account;
import com.budget_app.repository.account.AccountRepository;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.dto.accountBalance.AccountBalanceRequest;
import com.budget_app.repository.accountBalance.AccountBalanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountBalanceService extends BaseService<AccountBalance, Long, AccountBalanceRequest, AccountBalanceResponse> {

    private final AccountBalanceRepository accountBalanceRepository;
    private final AccountRepository accountRepository;
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

    public void updateFields(AccountBalance newBalance, AccountBalance oldBalance) {
        oldBalance.setDate(newBalance.getDate()).setAccount(newBalance.getAccount()).setBalance(newBalance.getBalance());
    }

    public ResponseEntity<AccountBalanceResponse> create(AccountBalanceRequest request) {
//        Account account = accountRepository.findById(request.accountId())
//                .orElseThrow(() -> new RuntimeException("Account Not Found"));
        Account account = getAccount(request.accountId());

        AccountBalance balance = new AccountBalance()
                .setDate(request.date())
                .setBalance(request.balance())
                .setAccount(account);

        repository.save(balance);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
