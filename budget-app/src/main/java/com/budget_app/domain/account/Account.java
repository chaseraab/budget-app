package com.budget_app.domain.account;

import com.budget_app.domain.accountBalance.AccountBalance;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Boolean isActive;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AccountBalance> accountBalances;

    public Long getId() {return id;}
    public Account setId(Long Id) {this.id = id; return this;}
    public String getName() {return name;}
    public Account setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public Account setType(String type) {this.type = type; return this;}
    public Boolean getIsActive() {return isActive;}
    public Account setIsActive(Boolean isActive) {this.isActive = isActive; return this;}
    public List<AccountBalance> getAccountBalances() {return accountBalances;}
    public Account setAccountBalances(List<AccountBalance> accountBalances) {this.accountBalances = accountBalances; return this;}

    public void addAccountBalance(AccountBalance accountBalance) {
        accountBalances.add(accountBalance);
    }

    public void updateAccountBalance(Long id, AccountBalance accountBalance) {
        AccountBalance existing = accountBalances.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find AccountBalance"));
        existing.setAccount(accountBalance.getAccount())
                .setBalance(accountBalance.getBalance())
                .setBudget(accountBalance.getBudget())
                .setDate(LocalDate.now());
    }

    public void removeAccountBalancce(Long id) {
        accountBalances.removeIf(a -> Objects.equals(a.getId(), id));
    }

}