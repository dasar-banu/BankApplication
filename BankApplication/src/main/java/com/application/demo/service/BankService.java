package com.application.demo.service;

import java.util.List;

import com.application.demo.model.Customer;
import com.application.demo.model.Transactions;

public interface BankService {

	public Customer createAccount(Customer cust);
	
	public List<Customer> getAllAccountDeatils();
	
	public void removeAccount(Long acc);

	public Customer updateAccount(Long id, Customer cust);
	
	public Transactions depositAmount(Transactions trans);
	
	public Transactions withdrawAmount(Transactions trans);
	
	public List<Transactions> getTransactions(Long id);
	
}
