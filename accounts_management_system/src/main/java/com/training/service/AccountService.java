package com.training.service;

import com.training.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountById(Long id);
    List<Account> getAccountsByCustomerId(String customerId);
    List<Account> getAllAccounts();
    boolean updateAccount(Account account);
    boolean deleteAccount(Long id);
}
