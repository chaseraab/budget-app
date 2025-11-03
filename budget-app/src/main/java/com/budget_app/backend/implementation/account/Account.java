package com.budget_app.backend.implementation.account;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    public Long getId() {return id;}
    public Account setId(Long Id) {this.id = id; return this;}
    public String getName() {return name;}
    public Account setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public Account setType(String type) {this.type = type; return this;}
}