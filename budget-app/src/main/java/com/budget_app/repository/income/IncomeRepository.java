package com.budget_app.repository.income;

import com.budget_app.domain.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
