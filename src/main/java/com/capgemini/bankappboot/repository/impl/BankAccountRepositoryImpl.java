
package com.capgemini.bankappboot.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappboot.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
		double balance = jdbcTemplate.queryForObject("SELECT balance FROM bankaccount WHERE account_id =?",
				new Object[] { accountId }, Double.class);
		// System.out.println("balance");
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("UPDATE bankaccount SET balance = ? WHERE account_id = ?",
				new Object[] { newBalance, accountId });
		return count != 0;
	}
}