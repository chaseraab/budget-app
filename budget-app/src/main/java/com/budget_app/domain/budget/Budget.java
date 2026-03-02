package com.budget_app.domain.budget;

import com.budget_app.domain.accountBalance.AccountBalance;
import com.budget_app.domain.allocation.budget.AllocationBudget;
import com.budget_app.domain.allocation.snapshot.AllocationSnapshot;
import com.budget_app.dto.allocation.budget.AllocationBudgetRequest;
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

//    @OneToMany(
//            mappedBy = "budget",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Transaction> transactions = new ArrayList<>();

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

    public void addAllocationFromSnapshot(AllocationSnapshot allocationSnapshot) {
        AllocationBudget allocationBudget = new AllocationBudget()
                .setName(allocationSnapshot.getName())
                .setType(allocationSnapshot.getType())
                .setAmount(allocationSnapshot.getAmount());
        this.addAllocation(allocationBudget);
    }

    public void addAllocationFromRequest(AllocationBudgetRequest request) {
        AllocationBudget allocationBudget = new AllocationBudget()
                .setName(request.name())
                .setType(request.type())
                .setAmount(request.amount());
        this.addAllocation(allocationBudget);
    }

    private void addAllocation(AllocationBudget allocation) {
        allocations.add(allocation);
        allocation.setBudget(this);
    }

    public void removeAllocation(Long id) {
        allocations.removeIf(a -> Objects.equals(a.getId(), id));
    }

    public void updateAllocation(Long id, AllocationBudgetRequest request) {
        AllocationBudget allocation = allocations.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Unable to find allocation"));
        allocation.setName(request.name())
                .setType(request.type())
                .setAmount(request.amount());
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
        newBalance.setDate(this.month.atEndOfMonth());
        addEndOfMonthAccountBalance(newBalance);
    }

    public void setInitialStartOfMonthAccountBalances(AccountBalance accountBalance) {
        AccountBalance newBalance = createNewAccountBalance(accountBalance);
        newBalance.setDate(this.month.atDay(1));
        addStartOfMonthAccountBalance(newBalance);
    }

    public void addStartOfMonthAccountBalance(AccountBalance accountBalance) {
        startOfMonthBalances.add(accountBalance);
        accountBalance.setBudget(this);
    }

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

}
