package com.budget_app.service.budget;

import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.domain.budget.Budget;
import com.budget_app.dto.budget.BudgetRequest;
import com.budget_app.dto.budget.BudgetResponse;
import com.budget_app.mapper.budget.BudgetMapper;
import com.budget_app.repository.allocation.budget.AllocationBudgetRepository;
import com.budget_app.repository.allocation.snapshot.AllocationSnapshotRepository;
import com.budget_app.repository.budget.BudgetRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class BudgetService extends BaseService<Budget, Long, BudgetRequest, BudgetResponse> {

    private final BudgetMapper mapper;
    private final AllocationBudgetRepository allocationBudgetRepository;
    private final AllocationSnapshotRepository allocationSnapshotRepository;

    public BudgetService(BudgetRepository repository, BudgetMapper mapper, AllocationBudgetRepository allocationBudgetRepository, AllocationSnapshotRepository allocationSnapshotRepository) {
        super(repository);
        this.mapper = mapper;
        this.allocationBudgetRepository = allocationBudgetRepository;
        this.allocationSnapshotRepository = allocationSnapshotRepository;
    }

    private List<AllocationBudget> createAllocationBudgets(Budget budget) {
        return allocationSnapshotRepository.findByIsActiveTrue()
                .stream().map(snapshot -> new AllocationBudget()
                            .setName(snapshot.getName())
                            .setType(snapshot.getType())
                            .setAmount(snapshot.getAmount())
                            .setBudget(budget)
            )
        .toList();
    }

    public Budget toEntity(BudgetRequest request) {return mapper.toEntity(request);}
    public BudgetResponse toResponse(Budget budget) {return mapper.toResponse(budget);}

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
        Budget budget = new Budget()
                .setAllocations(createAllocationBudgets(budget));
    }

}
