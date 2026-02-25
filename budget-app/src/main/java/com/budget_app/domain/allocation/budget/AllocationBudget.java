package com.budget_app.domain.allocation.budget;

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
    private boolean isActive;
    private boolean isExpected;

    public Long getId() {return id;}
    public String getName() {return name;}
    public AllocationBudget setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public AllocationBudget setType(String type) {this.type = type; return this;}
    public double getAmount() {return this.amount;}
    public AllocationBudget setAmount(Double amount) {this.amount = amount; return this;}
    public boolean getIsActive() {return this.isActive;}
    public AllocationBudget setIsActive(boolean isActive) {this.isActive = isActive; return this;}
    public boolean getIsExpected() {return this.isExpected;}
    public AllocationBudget setIsExpected(boolean isExpected) {this.isExpected = isExpected; return this;}
}
