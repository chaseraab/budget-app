package com.budget_app.repository.allocation;

import com.budget_app.domain.allocation.Allocation;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends BaseRepository<Allocation, Long> {
}
