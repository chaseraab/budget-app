package com.budget_app.domain.allocation.budget;

import com.budget_app.domain.budget.Budget;
import com.budget_app.domain.transaction.Transaction;
import com.budget_app.dto.transaction.TransactionRequest;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "allocation_budgets")
public class AllocationBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetid", nullable = false)
    private Budget budget;

    @OneToMany(
            mappedBy = "allocation",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Long getId() {return id;}
    public String getName() {return name;}
    public AllocationBudget setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public AllocationBudget setType(String type) {this.type = type; return this;}
    public double getAmount() {return this.amount;}
    public AllocationBudget setAmount(Double amount) {this.amount = amount; return this;}
    public Budget getBudget() {return budget;}
    public AllocationBudget setBudget(Budget budget) {this.budget = budget; return this;}
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public AllocationBudget setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        return this;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void removeTransaction(Long id) {
        transactions.removeIf(a -> Objects.equals(a.getId(), id));
    }

    public void updateTransaction(Long id, Transaction newTransaction) {
        Transaction transaction = transactions.stream().filter(a -> Objects.equals(id, a.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unable to find transaction"));
        transaction.setAccount(newTransaction.getAccount())
                .setAllocation(newTransaction.getAllocation())
                .setDate(newTransaction.getDate())
                .setItem(newTransaction.getItem())
                .setCompany(newTransaction.getCompany())
                .setAmount(newTransaction.getAmount());
    }
}
