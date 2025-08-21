package com.training.service;

import com.training.model.AccountBalance;

/**
 * @author Hasmita
 **/
public interface AccountBalanceService {
    boolean updateAccountBalance(Long accountId, double amount, String transactionType);
    AccountBalance getAccountBalance(Long accountId);
}
