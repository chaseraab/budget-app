package com.budget_app.repository.income.snapshot;

import com.budget_app.domain.income.snapshot.IncomeSnapshot;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeSnapshotRepository extends BaseRepository<IncomeSnapshot, Long> {}