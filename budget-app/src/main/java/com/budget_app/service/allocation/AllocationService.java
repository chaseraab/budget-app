package com.budget_app.service.allocation;

import com.budget_app.domain.allocation.Allocation;
import com.budget_app.dto.allocation.AllocationRequest;
import com.budget_app.dto.allocation.AllocationResponse;
import com.budget_app.mapper.allocation.AllocationMapper;
import com.budget_app.repository.allocation.AllocationRepository;
import com.budget_app.service.base.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AllocationService extends BaseService<Allocation, Long, AllocationRequest, AllocationResponse> {

    //private final AllocationRepository allocationRepository;
    private final AllocationMapper mapper;

    public AllocationService(AllocationRepository repository, AllocationMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public Allocation toEntity(AllocationRequest req) {
        return mapper.toEntity(req);
    }

    public AllocationResponse toResponse(Allocation allocation) {
        return mapper.toResponse(allocation);
    }

    public ResponseEntity<AllocationResponse> update(Long id, AllocationRequest request) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(request.name());
                    existing.setType(request.type());
                    existing.setAmount(request.amount());
                    existing.setIsTemp(request.isTemp());
                    existing.setIsExpected(request.isExpected());
                    repository.save(existing);
                    return ResponseEntity.ok(toResponse(existing));
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
