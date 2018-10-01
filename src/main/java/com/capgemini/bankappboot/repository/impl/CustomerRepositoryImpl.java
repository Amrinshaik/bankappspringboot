package com.capgemini.bankappboot.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankappboot.entities.BankAccount;
import com.capgemini.bankappboot.entities.Customer;
import com.capgemini.bankappboot.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) throws DataAccessException{
		try{return jdbcTemplate.queryForObject(
				"SELECT * FROM customer inner join bankaccount on customer.customer_id = bankaccount.customer_id where customer.email=? and customer.password=?",
				new Object[] { customer.getEmail(), customer.getPassword() }, new CustomerRowMapper());
		}
		catch(DataAccessException e) {
			e.initCause(new EmptyResultDataAccessException("not found",1));
			throw e;
		}
	}

	@Override
	public Customer updateProfile(Customer customer) {
		int count = jdbcTemplate.update(
				"update customer set customer_name= ? ,password= ? ,email= ? ,address= ? , dateofbirth= ? where customer_id= ? ",
				new Object[] { customer.getCustomerName(), customer.getPassword(), customer.getEmail(),
						customer.getAddress(), customer.getDateOfBirth(), customer.getCustomerId() });
		return count != 0 ? customer : findCustomerById(customer.getCustomerId());
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		int count = jdbcTemplate.update("update customer set password= ? WHERE customer_id = ? AND password = ?",
				new Object[] { newPassword, customer.getCustomerId(), oldPassword });
		return count != 0;
	}

	@Override
	public Customer findCustomerById(int customerId) {
		return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE customer_Id=?", new Object[] { customerId },
				new CustomerRowMapper());
	}

	private class CustomerRowMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

			Customer customer = new Customer();
			BankAccount account = new BankAccount();
			account.setAccountId(rs.getInt(7));
			account.setAccountType(rs.getString(8));
			account.setBalance(rs.getDouble(9));

			customer.setCustomerId(rs.getInt(1));
			customer.setCustomerName(rs.getString(2));
			customer.setPassword(rs.getString(3));
			customer.setEmail(rs.getString(4));
			customer.setAddress(rs.getString(5));
			customer.setDateOfBirth(rs.getDate(6).toLocalDate());
			customer.setAccount(account);

			return customer;
		}
	}
}