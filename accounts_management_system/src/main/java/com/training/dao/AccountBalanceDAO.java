package com.training.dao;

import com.training.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hasmita
 **/
public interface AccountBalanceDAO extends JpaRepository<AccountBalance, Long> {

}
