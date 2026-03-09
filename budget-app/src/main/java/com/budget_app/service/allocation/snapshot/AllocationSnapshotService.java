package com.budget_app.service.allocation.snapshot;

import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotRequest;
import com.budget_app.dto.allocation.snapshot.AllocationSnapshotResponse;
import com.budget_app.mapper.allocation.snapshot.AllocationSnapshotMapper;
import com.budget_app.repository.allocation.snapshot.AllocationSnapshotRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AllocationSnapshotService extends BaseService<AllocationSnapshot, Long, AllocationSnapshotRequest, AllocationSnapshotResponse> {

    private final AllocationSnapshotMapper mapper;

    public AllocationSnapshotService(AllocationSnapshotRepository repository, AllocationSnapshotMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public AllocationSnapshot toEntity(AllocationSnapshotRequest req) {
        return mapper.toEntity(req);
    }

    public AllocationSnapshotResponse toResponse(AllocationSnapshot allocation) {
        return mapper.toResponse(allocation);
    }

    public ResponseEntity<AllocationSnapshotResponse> update(Long id, AllocationSnapshotRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setType(request.type());
                    existing.setAmount(request.amount());
//                    Budget budget = budgetRepository.findById(request.budgetId())
//                                    .orElseThrow(() -> new RuntimeException("Budget not found"));
//                    existing.setBudget(budget);
                    existing.setIsActive(request.isActive());
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
