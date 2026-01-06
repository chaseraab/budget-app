package com.budget_app.service.income;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.income.Income;
import com.budget_app.dto.income.IncomeRequest;
import com.budget_app.dto.income.IncomeResponse;
import com.budget_app.mapper.income.IncomeMapper;
import com.budget_app.repository.account.AccountRepository;
import com.budget_app.repository.income.IncomeRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IncomeService extends BaseService<Income, Long, IncomeRequest, IncomeResponse> {

    private final AccountRepository accountRepository;
    private final IncomeMapper mapper;

    public IncomeService(IncomeRepository incomeRepository, AccountRepository accountRepository, IncomeMapper mapper) {
        super(incomeRepository);
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    private Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Income toEntity(IncomeRequest req) { return mapper.toEntity(req); }

    @Override
    public IncomeResponse toResponse(Income entity) { return mapper.toResponse(entity); }

    public ResponseEntity<IncomeResponse> update(Long id, IncomeRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    Account account = accountRepository.findById(request.accountId())
                            .orElseThrow(() -> new RuntimeException("Account not found"));
                    existing.setAccount(account);
                    existing.setAmount(request.amount());
                    existing.setName(request.name());
                    existing.setIsRecurring(request.isRecurring());

                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<IncomeResponse> create(IncomeRequest request) {
        Account account = getAccount(request.accountId());

        Income income = new Income()
                .setAmount(request.amount())
                .setName(request.name())
                .setIsRecurring(request.isRecurring())
                .setAccount(account);

        repository.save(income);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
