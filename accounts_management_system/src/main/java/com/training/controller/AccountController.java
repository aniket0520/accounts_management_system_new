package com.training.controller;

import com.training.model.Account;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('TELLER')")
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account created = accountService.createAccount(account);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN','TELLER')")
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return (account != null)
                ? new ResponseEntity<>(account, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','TELLER')")
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','CUSTOMER','TELLER')")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerId(customerId));
    }

//    @PutMapping
//    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
//        boolean updated = accountService.updateAccount(account);
//        return updated
//                ? ResponseEntity.ok("Account updated successfully.")
//                : new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
//    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','TELLER')")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setAccountId(id);
        boolean updated = accountService.updateAccount(account);
        Map<String, String> response = new HashMap<>();
        if (updated) {
            response.put("message", "Account updated successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Account not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
//        boolean deleted = accountService.deleteAccount(id);
//        return deleted
//                ? ResponseEntity.ok("Account deleted successfully.")
//                : new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
//    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','TELLER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable Long id) {
        boolean deleted = accountService.deleteAccount(id);
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Account deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Account not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
