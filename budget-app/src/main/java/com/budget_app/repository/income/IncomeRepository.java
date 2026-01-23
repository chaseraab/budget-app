package com.budget_app.repository.income;

import com.budget_app.domain.income.Income;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends BaseRepository<Income, Long> {}