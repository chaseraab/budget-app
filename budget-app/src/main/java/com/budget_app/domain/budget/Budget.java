package com.budget_app.domain.budget;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.transaction.Transaction;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private YearMonth month;

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AllocationBudget> allocations = new ArrayList<>();

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccountBalance> startOfMonthBalances = new ArrayList<>();

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccountBalance> endOfMonthBalances = new ArrayList<>();

    public Long getId() {return id;}
    public String getName() {return name;}
    public Budget setName(String name) {this.name = name; return this;}
    public YearMonth getMonth() {return month;}
    public Budget setMonth(YearMonth month) {this.month = month; return this;}
    public List<AllocationBudget> getAllocations() {return allocations;}
    public Budget setAllocations(List<AllocationBudget> allocations) {this.allocations = allocations; return this;}


    public void addAllocation(AllocationBudget allocation) {
        allocations.add(allocation);
        allocation.setBudget(this);
    }

    public void removeAllocation(Long id) {
        allocations = allocations.stream().filter(s -> !(Objects.equals(s.getId(), id))).toList();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Long id) {
        transactions = transactions.stream().filter(s -> !(Objects.equals(s.getId(), id))).toList();
    }

    public void addStartOfMonthAccountBalance(AccountBalance accountBalance) {
        startOfMonthBalances.add(accountBalance);
    }

    public void removeStartOfMonthAccountBalance(Long id) {
        startOfMonthBalances = startOfMonthBalances.stream().filter(s -> !(Objects.equals(s.getId(), id))).toList();
    }

    public void addEndOfMonthAccountBalance(AccountBalance accountBalance) {
        endOfMonthBalances.add(accountBalance);
    }

    public void removeendOfMonthAccountBalance(Long id) {
        endOfMonthBalances = endOfMonthBalances.stream().filter(s -> !(Objects.equals(s.getId(), id))).toList();
    }

}
