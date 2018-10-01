package com.capgemini.bankappboot.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.bankappboot.exceptions.LowBalanceException;
import com.capgemini.bankappboot.repository.BankAccountRepository;
import com.capgemini.bankappboot.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepository bankAccountRepository;
	@Override
	public double getBalance(long accountId)  {
		return bankAccountRepository.getBalance(accountId);
	
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) { // checking if bank account exists
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			} else
				throw new LowBalanceException("You donot have sufficient balance.");
		}
		return balance; // -1 is returned
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws LowBalanceException {
		double balance = withdraw(fromAcc, amount);
		if (balance != -1) {
			if (deposit(toAcc, amount) == -1) {
				return false;
			}
			return true;
		}
		return false;
	}
}