package com.budget_app.service.allocation.snapshot;

import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.budget.Budget;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.allocation.budget.AllocationBudgetResponse;
import com.budget_app.mapper.allocation.budget.AllocationBudgetMapper;
import com.budget_app.repository.allocation.budget.AllocationBudgetRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AllocationSnapshotService extends BaseService<AllocationBudget, Long, AllocationBudgetRequest, AllocationBudgetResponse> {

    private final AllocationBudgetMapper mapper;

    public AllocationSnapshotService(AllocationBudgetRepository repository, AllocationBudgetMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public AllocationBudget toEntity(AllocationBudgetRequest req) {
        return mapper.toEntity(req);
    }

    public AllocationBudgetResponse toResponse(AllocationBudget allocation) {
        return mapper.toResponse(allocation);
    }

    public ResponseEntity<AllocationBudgetResponse> update(Long id, AllocationBudgetRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setType(request.type());
                    existing.setAmount(request.amount());
                    Budget budget = budgetRepository.findById(request.budgetId())
                                    .orElseThrow(() -> new RuntimeException("Budget not found"));
                    existing.setBudget(budget);
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
