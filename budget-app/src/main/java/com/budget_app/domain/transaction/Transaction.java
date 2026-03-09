package com.budget_app.domain.transaction;

import com.budget_app.domain.account.Account;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.budget.Budget;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountid", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allocationid", nullable = false)
    private AllocationBudget allocation;

    private LocalDate date;
    private String item;
    private String company;
    private double amount;

    public Long getId() {return id;}
    public LocalDate getDate() {return date;}
    public Transaction setDate(LocalDate date) {this.date = date; return this;}
    public String getItem() {return item;}
    public Transaction setItem(String item) {this.item = item; return this;}
    public String getCompany() {return company;}
    public Transaction setCompany(String company) {this.company = company; return this;}
    public Account getAccount() {return account;}
    public Transaction setAccount(Account account) {this.account = account; return this;}
    public double getAmount() {return amount;}
    public Transaction setAmount(double amount) {this.amount = amount; return this;}
    public AllocationBudget getAllocation() {return allocation;}
    public Transaction setAllocation(AllocationBudget allocation) {this.allocation = allocation; return this;}
}
