package com.budget_app.repository.allocation.snapshot;

import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllocationSnapshotRepository extends BaseRepository<AllocationSnapshot, Long> {
    List<AllocationSnapshot> findByIsActiveTrue();
}
