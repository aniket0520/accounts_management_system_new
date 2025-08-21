package com.training.dto;

public class AccountBalanceDTO {
    private Long accountId;
    private double balance;

    public AccountBalanceDTO(Long accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
}
