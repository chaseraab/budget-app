package com.budget_app.domain.allocation;

import jakarta.persistence.*;

@Entity
@Table(name = "allocations")
public class Allocation {

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
    public Allocation setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public Allocation setType(String type) {this.type = type; return this;}
    public double getAmount() {return this.amount;}
    public Allocation setAmount(Double amount) {this.amount = amount; return this;}
    public boolean getIsActive() {return this.isActive;}
    public Allocation setIsActive(boolean isActive) {this.isActive = isActive; return this;}
    public boolean getIsExpected() {return this.isExpected;}
    public Allocation setIsExpected(boolean isExpected) {this.isExpected = isExpected; return this;}
}
