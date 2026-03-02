package com.budget_app.service.budget;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.domain.budget.Budget;
import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.budget.BudgetRequest;
import com.budget_app.dto.budget.BudgetResponse;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.mapper.budget.BudgetMapper;
import com.budget_app.repository.account.AccountRepository;
import com.budget_app.repository.accountBalance.AccountBalanceRepository;
import com.budget_app.repository.allocation.snapshot.AllocationSnapshotRepository;
import com.budget_app.repository.budget.BudgetRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BudgetService extends BaseService<Budget, Long, BudgetRequest, BudgetResponse> {

    private final BudgetMapper mapper;
    private final AllocationSnapshotRepository allocationSnapshotRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final AccountRepository accountRepository;

    public BudgetService(BudgetRepository repository, BudgetMapper mapper, AllocationSnapshotRepository allocationSnapshotRepository, AccountBalanceRepository accountBalanceRepository, AccountRepository accountRepository) {
        super(repository);
        this.mapper = mapper;
        this.allocationSnapshotRepository = allocationSnapshotRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountRepository = accountRepository;
    }

    public Budget toEntity(BudgetRequest request) {return mapper.toEntity(request);}
    public BudgetResponse toResponse(Budget budget) {return mapper.toResponse(budget);}

    private Budget findBudget(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
    }

    private Account findAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    private Transaction creationTransactionFromRequest(TransactionRequest request) {
        Account account = findAccount(request.accountId());
        return new Transaction().setAccount(account)
                .setDate(request.date())
                .setItem(request.item())
                .setCompany(request.company())
                .setAmount(request.amount());
    }

    public ResponseEntity<BudgetResponse> update(Long id, BudgetRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setMonth(request.month());
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<BudgetResponse> create(BudgetRequest request) {
        Budget budget = new Budget();
        budget.setName(request.name())
                .setMonth(request.month());

        List<AllocationSnapshot> allocations = allocationSnapshotRepository.findByIsActiveTrue();
        allocations.forEach(budget::addAllocationFromSnapshot);

        List<AccountBalance> balances = accountBalanceRepository.findLatestPerActiveAccount();
        balances.forEach(budget::setInitialEndOfMonthAccountBalances);

        repository.save(budget);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<BudgetResponse> addAllocation(Long id, AllocationBudgetRequest request) {
        Budget budget = findBudget(id);
        budget.addAllocationFromRequest(request);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<String> deleteAllocation(Long budgetId, Long allocationId) {
        Budget budget = findBudget(budgetId);
        budget.removeAllocation(allocationId);
        repository.save(budget);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<BudgetResponse> updateAllocation(Long budgetId, AllocationBudgetRequest request) {
        return repository.findById(budgetId)
                .map(existing -> {
                    existing.updateAllocation(budgetId, request);
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<BudgetResponse> addTransaction(Long budgetId, Long allocationId, TransactionRequest request) {
        Budget budget = findBudget(budgetId);
        AllocationBudget allocation = budget.findAllocation(allocationId);
        Transaction transaction = creationTransactionFromRequest(request)
            .setAllocation(allocation);

        allocation.addTransaction(transaction);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<BudgetResponse> removeTransaction(Long budgetId, Long allocationId, Long transactionId) {
        Budget budget = findBudget(budgetId);
        AllocationBudget allocation = budget.findAllocation(allocationId);
        allocation.removeTransaction(transactionId);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<BudgetResponse> updateTransaction(Long budgetId, Long allocationId, Long transactionId, TransactionRequest request) {
        Budget budget = findBudget(budgetId);
        AllocationBudget allocation = budget.findAllocation(allocationId);
        Transaction transaction = creationTransactionFromRequest(request)
                .setAllocation(allocation);
        allocation.updateTransaction(transactionId, transaction);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }
}
