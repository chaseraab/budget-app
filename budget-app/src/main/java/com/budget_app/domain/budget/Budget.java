package com.budget_app.domain.budget;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.accountBalance.BalanceType;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.income.budget.IncomeBudget;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "date")
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
    private List<IncomeBudget> income = new ArrayList<>();

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @org.hibernate.annotations.SQLRestriction("type = 'START'")
    private List<AccountBalance> startOfMonthBalances = new ArrayList<>();

    @OneToMany(
            mappedBy = "budget",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @org.hibernate.annotations.SQLRestriction("type = 'END'")
    private List<AccountBalance> endOfMonthBalances = new ArrayList<>();

    public Long getId() {return id;}
    public String getName() {return name;}
    public Budget setName(String name) {this.name = name; return this;}
    public YearMonth getMonth() {return month;}
    public Budget setMonth(YearMonth month) {this.month = month; return this;}
    public List<AllocationBudget> getAllocations() {return allocations;}
    public Budget setAllocations(List<AllocationBudget> allocations) {this.allocations = allocations; return this;}

    public void addIncome(IncomeBudget income) {
        this.income.add(income);
        income.setBudget(this);
    }

    public void removeIncome(Long id) {
        income.removeIf(a -> Objects.equals(a.getId(), id));
    }

    public void updateIncome(Long id, IncomeBudget income) {
        IncomeBudget existing = this.income.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find allocation"));
        existing.setAmount(income.getAmount())
                .setName(income.getName())
                .setAccount(income.getAccount());
    }

    public List<IncomeBudget> getIncome() {return this.income;}
    public Budget setIncome(List<IncomeBudget> income) {this.income = income; return this;}

    public void addAllocation(AllocationBudget allocation) {
        allocations.add(allocation);
        allocation.setBudget(this);
    }

    public void removeAllocation(Long id) {
        allocations.removeIf(a -> Objects.equals(a.getId(), id));
    }

    public void updateAllocation(Long id, AllocationBudget request) {
        AllocationBudget allocation = allocations.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find allocation"));
        allocation.setName(request.getName())
                .setType(request.getType())
                .setAmount(request.getAmount());
    }

    public AllocationBudget findAllocation(long id) {
        return allocations.stream().filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Allocation not found"));
    }

    private AccountBalance createNewAccountBalance(AccountBalance accountBalance) {
        AccountBalance newBalance = new AccountBalance()
                .setAccount(accountBalance.getAccount())
                .setAccount(accountBalance.getAccount())
                .setBalance(accountBalance.getBalance());
        return newBalance;
    }

    public void setInitialEndOfMonthAccountBalances(AccountBalance accountBalance) {
        AccountBalance newBalance = createNewAccountBalance(accountBalance);
        newBalance.setDate(this.month.atEndOfMonth())
                .setType(BalanceType.END);
        addEndOfMonthAccountBalance(newBalance);
    }

    public void setInitialStartOfMonthAccountBalances(AccountBalance accountBalance) {
        AccountBalance newBalance = createNewAccountBalance(accountBalance);
        newBalance.setDate(this.month.atDay(1))
                .setType(BalanceType.START);
        addStartOfMonthAccountBalance(newBalance);
    }

    public void addStartOfMonthAccountBalance(AccountBalance accountBalance) {
        startOfMonthBalances.add(accountBalance);
        accountBalance.setBudget(this);
    }

    public List<AccountBalance> getStartOfMonthBalances() {return startOfMonthBalances;}
    public Budget setStartOfMonthBalances(List<AccountBalance> startOfMonthBalances) {this.startOfMonthBalances = startOfMonthBalances; return this;}

    public void removeStartOfMonthAccountBalance(AccountBalance accountBalance) {
        startOfMonthBalances = startOfMonthBalances.stream().filter(s -> !(Objects.equals(s.getId(), accountBalance.getId()))).toList();
        accountBalance.setBudget(null);
    }

    public void addEndOfMonthAccountBalance(AccountBalance accountBalance) {
        endOfMonthBalances.add(accountBalance);
        accountBalance.setBudget(this);
    }

    public void removeEndOfMonthAccountBalance(AccountBalance accountBalance) {
        endOfMonthBalances = endOfMonthBalances.stream().filter(s -> !(Objects.equals(s.getId(), accountBalance.getId()))).toList();
        accountBalance.setBudget(null);
    }

    public List<AccountBalance> getEndOfMonthBalances() {return endOfMonthBalances;}
    public Budget setEndOfMonthBalances(List<AccountBalance> endOfMonthBalances) {this.endOfMonthBalances = endOfMonthBalances; return this;}

}
