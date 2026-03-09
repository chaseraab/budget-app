package com.budget_app.domain.allocation.snapshot;

import jakarta.persistence.*;

@Entity
@Table(name = "allocation_snapshots")
public class AllocationSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double amount;
    private boolean isActive;

    public Long getId() {return id;}
    public String getName() {return name;}
    public AllocationSnapshot setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public AllocationSnapshot setType(String type) {this.type = type; return this;}
    public double getAmount() {return this.amount;}
    public AllocationSnapshot setAmount(Double amount) {this.amount = amount; return this;}
    public boolean getIsActive() {return this.isActive;}
    public AllocationSnapshot setIsActive(boolean isActive) {this.isActive = isActive; return this;}
}
