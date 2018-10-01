package com.capgemini.bankappboot.service;

import com.capgemini.bankappboot.entities.Customer;
import com.capgemini.bankappboot.exceptions.UserNotFoundException;


public interface CustomerService {
	public Customer authenticate(Customer customer) throws UserNotFoundException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
}
