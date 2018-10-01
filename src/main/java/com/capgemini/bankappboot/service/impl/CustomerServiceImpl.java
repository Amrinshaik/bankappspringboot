package com.capgemini.bankappboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankappboot.entities.Customer;
import com.capgemini.bankappboot.exceptions.UserNotFoundException;
import com.capgemini.bankappboot.repository.CustomerRepository;
import com.capgemini.bankappboot.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

@Override
public Customer authenticate(Customer customer) throws UserNotFoundException {
	try{
		return customerRepository.authenticate(customer);
	}
	catch(DataAccessException ex){
		UserNotFoundException u= new UserNotFoundException("Customer Not Found");
		u.initCause(ex);
		throw u;
	}
}

@Override
public Customer updateProfile(Customer customer) {
	return customerRepository.updateProfile(customer);
}

@Override
public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
	return customerRepository.updatePassword(customer, oldPassword, newPassword);
}
}