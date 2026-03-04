package com.budget_app.repository.income.snapshot;

import com.budget_app.domain.income.snapshot.IncomeSnapshot;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeSnapshotRepository extends BaseRepository<IncomeSnapshot, Long> {
    List<IncomeSnapshot> findByIsActiveTrue();
}