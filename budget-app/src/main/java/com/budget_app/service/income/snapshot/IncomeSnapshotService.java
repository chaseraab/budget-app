package com.budget_app.service.income.snapshot;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.income.budget.IncomeBudget;
import com.budget_app.domain.income.snapshot.IncomeSnapshot;
import com.budget_app.dto.income.budget.IncomeBudgetRequest;
import com.budget_app.dto.income.budget.IncomeBudgetResponse;
import com.budget_app.dto.income.snapshot.IncomeSnapshotRequest;
import com.budget_app.dto.income.snapshot.IncomeSnapshotResponse;
import com.budget_app.mapper.income.snapshot.IncomeSnapshotMapper;
import com.budget_app.repository.account.AccountRepository;
import com.budget_app.repository.income.snapshot.IncomeSnapshotRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IncomeSnapshotService extends BaseService<IncomeSnapshot, Long, IncomeSnapshotRequest, IncomeSnapshotResponse> {

    private final AccountRepository accountRepository;
    private final IncomeSnapshotMapper mapper;

    public IncomeSnapshotService(IncomeSnapshotRepository incomeSnapshotRepository, AccountRepository accountRepository, IncomeSnapshotMapper mapper) {
        super(incomeSnapshotRepository);
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    private Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElse(null);
    }

    @Override
    public IncomeSnapshot toEntity(IncomeSnapshotRequest req) { return mapper.toEntity(req); }

    @Override
    public IncomeSnapshotResponse toResponse(IncomeSnapshot entity) { return mapper.toResponse(entity); }

    public ResponseEntity<IncomeSnapshotResponse> update(Long id, IncomeSnapshotRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    Account account = accountRepository.findById(request.accountId())
                            .orElseThrow(() -> new RuntimeException("Account not found"));
                    existing.setAccount(account);
                    existing.setAmount(request.amount());
                    existing.setName(request.name());
                    existing.setIsActive(request.isActive());

                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<IncomeSnapshotResponse> create(IncomeSnapshotRequest request) {
        Account account = getAccount(request.accountId());

        IncomeSnapshot incomeSnapshot = new IncomeSnapshot()
                .setAmount(request.amount())
                .setName(request.name())
                .setIsActive(request.isActive())
                .setAccount(account);

        repository.save(incomeSnapshot);

        return ResponseEntity.ok(toResponse(incomeSnapshot));
    }

}
