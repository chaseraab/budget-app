package com.budget_app.repository.income.budget;

import com.budget_app.domain.income.budget.IncomeBudget;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeBudgetRepository extends BaseRepository<IncomeBudget, Long> {}