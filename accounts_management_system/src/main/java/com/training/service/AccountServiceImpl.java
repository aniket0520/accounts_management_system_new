package com.training.service;

import com.training.dao.AccountBalanceDAO;
import com.training.dao.AccountDAO;
import com.training.model.Account;
import com.training.model.AccountBalance;
import com.training.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountBalanceDAO accountBalanceDAO;

    @Autowired
    private ProductService productService;

    @Autowired
    private KycDocumentService kycDocumentService;

    @Override
    public Account createAccount(Account account) {
        String customerId = kycDocumentService.getCustomerIdByDocNumber(account.getDocumentNumber());
        if (customerId == null) {
            throw new IllegalStateException(
                    "CustomerId not present  " + account.getDocumentNumber()
            );
        }
        account.setCustomerId(customerId);

        //Product Type validation

        Product product = productService.getProduct(account.getAccountType());
        if (product == null) {
            throw new IllegalArgumentException("Invalid product/account type: " + account.getAccountType());
        }


        double openingBalance = account.getOpeningBalance();
        if (openingBalance < product.getMinBalance()) {
            throw new IllegalArgumentException(
                    "Opening balance " + openingBalance +
                            " is less than required minimum balance " + product.getMinBalance()
            );
        }

        Account created = accountDAO.save(account);
        AccountBalance balance = new AccountBalance();
        balance.setBalance(openingBalance);
        balance.setAccount(created);
        created.setAccountBalance(balance);

        accountBalanceDAO.save(balance);

        return created;
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDAO.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAccountsByCustomerId(String customerId) {
        return accountDAO.findByCustomerId(customerId);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }

    @Override
    public boolean updateAccount(Account account) {
        if (accountDAO.existsById(account.getAccountId())) {
            accountDAO.save(account);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(Long id) {
        if (accountDAO.existsById(id)) {
            accountDAO.deleteById(id);
            return true;
        }
        return false;
    }
}
