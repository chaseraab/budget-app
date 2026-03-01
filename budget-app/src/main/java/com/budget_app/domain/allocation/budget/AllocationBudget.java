package com.budget_app.domain.allocation.budget;

import com.budget_app.domain.budget.Budget;
import jakarta.persistence.*;

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

    public Long getId() {return id;}
    public String getName() {return name;}
    public AllocationBudget setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public AllocationBudget setType(String type) {this.type = type; return this;}
    public double getAmount() {return this.amount;}
    public AllocationBudget setAmount(Double amount) {this.amount = amount; return this;}
    public Budget getBudget() {return budget;}
    public AllocationBudget setBudget(Budget budget) {this.budget = budget; return this;}
}
