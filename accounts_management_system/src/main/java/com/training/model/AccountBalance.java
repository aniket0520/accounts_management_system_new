package com.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "account_balances2")
public class AccountBalance {

    @Id
    private Long accountId;

    private double balance;

    @OneToOne
    @MapsId // Shared primary key
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    // --- Getters & Setters ---
    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
