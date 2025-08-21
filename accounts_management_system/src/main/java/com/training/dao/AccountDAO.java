package com.training.dao;

import com.training.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
    List<Account> findByAccountType(String accountType);
}
