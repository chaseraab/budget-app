package com.budget_app.domain.income.snapshot;

import com.budget_app.domain.account.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "income")
public class IncomeSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="accountid")
    private Account account;

    private double amount;
    private String name;
    private boolean isActive;

    public Long getId() {return id;}
    public Account getAccount() {return account;}
    public IncomeSnapshot setAccount(Account account) {this.account = account; return this;}
    public double getAmount() {return amount;}
    public IncomeSnapshot setAmount(double amount) {this.amount = amount; return this;}
    public String getName() {return name;}
    public IncomeSnapshot setName(String name) {this.name = name; return this;}
    public boolean getIsActive() {return isActive;}
    public IncomeSnapshot setIsActive(boolean isActive) {this.isActive = isActive; return this;}

}
