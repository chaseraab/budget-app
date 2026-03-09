package com.budget_app.repository.transaction;

import com.budget_app.domain.transaction.Transaction;
import com.budget_app.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    @Query("""
            SELECT t
            FROM Transaction t
            LEFT JOIN FETCH t.account
            LEFT JOIN FETCH t.allocation
            WHERE t.company = ?1
            AND t.amount = ?2
            AND t.date = ?3
            """)
    List<Transaction> findByConditions(String company, double amount, LocalDate date);
}