package com.capgemini.bankappboot.repository;

import com.capgemini.bankappboot.entities.Customer;

public interface CustomerRepository {
	public Customer authenticate(Customer customer);
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
	public Customer findCustomerById(int customerId);
}
