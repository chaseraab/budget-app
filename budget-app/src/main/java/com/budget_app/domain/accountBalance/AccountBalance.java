package com.budget_app.domain.accountBalance;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.budget.Budget;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "account_balances")
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Account account;
    private float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budgetid", nullable = false)
    private Budget budget;

    public Long getId() {return id;}
    public LocalDate getDate() {return date;}
    public AccountBalance setDate(LocalDate date) {this.date = date; return this;}
    public AccountBalance setAccount(Account account) {this.account = account; return this;}
    public Account getAccount() {return account;}
    public AccountBalance setBalance(float balance){this.balance = balance; return this;}
    public float getBalance() {return balance;}
    public Budget getBudget() {return budget;}
    public AccountBalance setBudget(Budget budget) {this.budget = budget; return this;}
}
