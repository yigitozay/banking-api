package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String accountNumber;
    private BigDecimal balance;
    private LocalDateTime createdAt;
    private List<transactions> transactions;

    protected AccountEntity(){ }

    public AccountEntity(String accountNumber, BigDecimal balance, LocalDateTime createdAt,List<transactions> transactions ){
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.createdAt = createdAt;
            this.transactions = transactions;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<transactions> transactions) {
        this.transactions = transactions;
    }
}
