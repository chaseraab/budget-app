package com.budget_app.repository.allocation;

import com.budget_app.domain.allocation.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {
}
