package com.budget_app.service.budget;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.domain.budget.Budget;
import com.budget_app.domain.income.budget.IncomeBudget;
import com.budget_app.domain.income.snapshot.IncomeSnapshot;
import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.budget.BudgetRequest;
import com.budget_app.dto.budget.BudgetResponse;
import com.budget_app.dto.income.budget.IncomeBudgetRequest;
import com.budget_app.dto.transaction.TransactionRequest;
import com.budget_app.mapper.budget.BudgetMapper;
import com.budget_app.repository.account.AccountRepository;
import com.budget_app.repository.accountBalance.AccountBalanceRepository;
import com.budget_app.repository.allocation.snapshot.AllocationSnapshotRepository;
import com.budget_app.repository.budget.BudgetRepository;
import com.budget_app.repository.income.snapshot.IncomeSnapshotRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BudgetService extends BaseService<Budget, Long, BudgetRequest, BudgetResponse> {

    private final BudgetMapper mapper;
    private final AllocationSnapshotRepository allocationSnapshotRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final AccountRepository accountRepository;
    private final IncomeSnapshotRepository incomeSnapshotRepository;

    public BudgetService(BudgetRepository repository, BudgetMapper mapper, AllocationSnapshotRepository allocationSnapshotRepository, AccountBalanceRepository accountBalanceRepository, AccountRepository accountRepository,
                         IncomeSnapshotRepository incomeSnapshotRepository) {
        super(repository);
        this.mapper = mapper;
        this.allocationSnapshotRepository = allocationSnapshotRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountRepository = accountRepository;
        this.incomeSnapshotRepository = incomeSnapshotRepository;
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

    private Transaction createTransactionFromRequest(TransactionRequest request) {
        Account account = findAccount(request.accountId());
        return new Transaction().setAccount(account)
                .setDate(request.date())
                .setItem(request.item())
                .setCompany(request.company())
                .setAmount(request.amount());
    }

    private AllocationBudget createAllocationFromRequest(AllocationBudgetRequest request) {
        return new AllocationBudget()
                .setName(request.name())
                .setType(request.type())
                .setAmount(request.amount());
    }

    private AllocationBudget createAllocationFromSnapshot(AllocationSnapshot allocationSnapshot) {
                return new AllocationBudget()
                .setName(allocationSnapshot.getName())
                .setType(allocationSnapshot.getType())
                .setAmount(allocationSnapshot.getAmount());
    }

    private IncomeBudget createIncomeFromSnapshot(IncomeSnapshot incomeSnapshot) {
        return new IncomeBudget()
                .setAccount(incomeSnapshot.getAccount())
                .setName(incomeSnapshot.getName())
                .setAmount(incomeSnapshot.getAmount())
    }

    private IncomeBudget createIncomeFromRequest(IncomeBudgetRequest request) {
        Account account = accountRepository.findById(request.accountId()).orElse(null);
        return new IncomeBudget()
                .setAccount(account)
                .setName(request.name())
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
        allocations.forEach(i -> {
            budget.addAllocation(createAllocationFromSnapshot(i));
        });

        List<AccountBalance> balances = accountBalanceRepository.findLatestPerActiveAccount();
        balances.forEach(budget::setInitialEndOfMonthAccountBalances);


        List<IncomeSnapshot> income = incomeSnapshotRepository.findByIsActiveTrue();
        income.forEach(i -> {
            budget.addIncome(createIncomeFromSnapshot(i));
        });

        repository.save(budget);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<BudgetResponse> addAllocation(Long id, AllocationBudgetRequest request) {
        Budget budget = findBudget(id);
        AllocationBudget allocation = createAllocationFromRequest(request)
                .setBudget(budget);
        budget.addAllocation(allocation);
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
        AllocationBudget allocation = createAllocationFromRequest(request);
        return repository.findById(budgetId)
                .map(existing -> {
                    existing.updateAllocation(budgetId, allocation);
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<BudgetResponse> addTransaction(Long budgetId, Long allocationId, TransactionRequest request) {
        Budget budget = findBudget(budgetId);
        AllocationBudget allocation = budget.findAllocation(allocationId);
        Transaction transaction = createTransactionFromRequest(request)
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
        Transaction transaction = createTransactionFromRequest(request)
                .setAllocation(allocation);
        allocation.updateTransaction(transactionId, transaction);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<BudgetResponse> addIncome(Long budgetId, IncomeBudgetRequest request) {
        Budget budget = findBudget(budgetId);
        IncomeBudget income = createIncomeFromRequest(request);
        budget.addIncome(income);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<BudgetResponse> removeIncome(Long budgetId, Long incomeId) {
        Budget budget = findBudget(budgetId);
        budget.removeIncome(incomeId);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

    public ResponseEntity<BudgetResponse> updateIncome(Long budgetId, Long incomeId, IncomeBudgetRequest request) {
        Budget budget = findBudget(budgetId);
        IncomeBudget income = createIncomeFromRequest(request);
        budget.updateIncome(incomeId, income);
        repository.save(budget);
        return ResponseEntity.ok(toResponse(budget));
    }

}
