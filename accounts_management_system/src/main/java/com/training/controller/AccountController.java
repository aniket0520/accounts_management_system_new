package com.training.controller;

import com.training.model.Account;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account created = accountService.createAccount(account);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return (account != null)
                ? new ResponseEntity<>(account, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }

    @PutMapping
    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
        boolean updated = accountService.updateAccount(account);
        return updated
                ? ResponseEntity.ok("Account updated successfully.")
                : new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        return deleted
                ? ResponseEntity.ok("Account deleted successfully.")
                : new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
    }
}
