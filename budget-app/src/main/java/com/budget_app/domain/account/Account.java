package com.budget_app.domain.account;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private Boolean isActive;

    public Long getId() {return id;}
    public Account setId(Long Id) {this.id = id; return this;}
    public String getName() {return name;}
    public Account setName(String name) {this.name = name; return this;}
    public String getType() {return type;}
    public Account setType(String type) {this.type = type; return this;}
    public Boolean getIsActive() {return isActive;}
    public Account setIsActive(Boolean isActive) {this.isActive = isActive; return this;}

}