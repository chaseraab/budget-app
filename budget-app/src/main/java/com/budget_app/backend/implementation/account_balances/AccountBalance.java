package com.budget_app.backend.implementation.account_balances;

import com.budget_app.backend.implementation.account.Account;
import jakarta.persistence.*;

@Entity
@Table(name = "account_balances")
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Account account;
    private float balance;

    public Long getId() {return id;}
    public AccountBalance setAccount(Account account) {this.account = account; return this;}
    public Account getAccount(){return account;}
    public AccountBalance setBalance(float balance){this.balance = balance; return this;}
    public float getBalance() {return balance;}
}
