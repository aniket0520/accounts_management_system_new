package com.training.controller;

import com.training.dto.AccountBalanceDTO;
import com.training.service.AccountBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hasmita
 **/

@RestController
@RequestMapping("/account-balances")
public class AccountBalanceController {
    @Autowired
    private AccountBalanceService accountBalanceService;

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccountBalance(@PathVariable Long id, @RequestBody double amount, @RequestParam String transactionType) {
        boolean updated = accountBalanceService.updateAccountBalance(id, amount, transactionType);
        return updated
                ? ResponseEntity.ok("Account balance updated successfully.")
                : new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountBalance(@PathVariable Long id) {
        var balance = accountBalanceService.getAccountBalance(id);
        if (balance == null) {
            return new ResponseEntity<>("Account not found.", HttpStatus.NOT_FOUND);
        }

        AccountBalanceDTO dto = new AccountBalanceDTO(balance.getAccountId(), balance.getBalance());
        return ResponseEntity.ok(dto);
    }
}

