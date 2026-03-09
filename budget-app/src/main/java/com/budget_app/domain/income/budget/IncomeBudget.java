package com.budget_app.domain.income.budget;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.budget.Budget;
import jakarta.persistence.*;

@Entity
@Table(name = "income_budgets")
public class IncomeBudget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="accountid")
    private Account account;

    private double amount;
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetid", nullable = false)
    private Budget budget;

    public Long getId() {return id;}
    public Account getAccount() {return account;}
    public IncomeBudget setAccount(Account account) {this.account = account; return this;}
    public double getAmount() {return amount;}
    public IncomeBudget setAmount(double amount) {this.amount = amount; return this;}
    public String getName() {return name;}
    public IncomeBudget setName(String name) {this.name = name; return this;}
    public Budget getBudget() {return budget;}
    public IncomeBudget setBudget(Budget budget) {this.budget = budget; return this;}
}
