package com.budget_app.domain.income;

import com.budget_app.domain.account.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="accountid")
    private Account account;

    private double amount;
    private String name;
    private boolean isRecurring;

    public Long getId() {return id;}
    public Account getAccount() {return account;}
    public Income setAccount(Account account) {this.account = account; return this;}
    public double getAmount() {return amount;}
    public Income setAmount(double amount) {this.amount = amount; return this;}
    public String getName() {return name;}
    public Income setName(String name) {this.name = name; return this;}
    public boolean getIsRecurring() {return isRecurring;}
    public Income setIsRecurring(boolean isRecurring) {this.isRecurring = isRecurring; return this;}
}
