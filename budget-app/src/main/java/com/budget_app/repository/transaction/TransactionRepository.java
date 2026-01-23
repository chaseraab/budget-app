package com.budget_app.repository.transaction;

import com.budget_app.domain.transaction.Transaction;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, Long> {
}