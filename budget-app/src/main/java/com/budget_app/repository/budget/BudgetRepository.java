package com.budget_app.repository.budget;

import com.budget_app.domain.budget.Budget;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends BaseRepository<Budget, Long> {
}
