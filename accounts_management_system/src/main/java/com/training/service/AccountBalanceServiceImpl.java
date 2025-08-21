package com.training.service;

import com.training.dao.AccountBalanceDAO;
import com.training.dao.AccountDAO;
import com.training.model.Account;
import com.training.model.AccountBalance;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hasmita
 **/

@Service
public class AccountBalanceServiceImpl implements AccountBalanceService {

    @Autowired
    private AccountBalanceDAO accountBalanceDAO;

    @Autowired
    private AccountDAO accountDAO;


    @Override
    @Transactional
    public boolean updateAccountBalance(Long accountId, double amount, String transactionType) {
        AccountBalance accountBalance = accountBalanceDAO.findById(accountId).orElse(null);
        Account account = accountDAO.findById(accountId).orElse(null);

        if (account == null) {
            return false; // Can't update a non-existing bank account
        }

        double newBalance;

        if (accountBalance == null) {
            accountBalance = new AccountBalance();
            accountBalance.setAccountId(accountId);

            if (transactionType.equalsIgnoreCase("CREDIT")) {
                newBalance = amount;
            } else if (transactionType.equalsIgnoreCase("DEBIT")) {
                newBalance = -amount;
            } else {
                return false;
            }
        } else {
            double currentBalance = accountBalance.getBalance();

            if (transactionType.equalsIgnoreCase("CREDIT")) {
                newBalance = currentBalance + amount;
            } else if (transactionType.equalsIgnoreCase("DEBIT")) {
                newBalance = currentBalance - amount;
            } else {
                return false;
            }
        }

        accountBalance.setBalance(newBalance);

        account.setAccountBalance(accountBalance);
        accountBalance.setAccount(account);

        accountBalanceDAO.save(accountBalance);
        accountDAO.save(account);

        return true;
    }


    @Override
    public AccountBalance getAccountBalance(Long accountId) {
        return accountBalanceDAO.findById(accountId).orElse(null);
    }
}
