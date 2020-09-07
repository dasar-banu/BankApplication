package com.application.demo;

import java.util.List;

public interface BankService {

	public Customer createAccount(Customer cust);
	
	public List<Customer> getAllAccountDeatils();
	
	public String removeAccount(Long acc);

	public Customer updateAccount(Long id, Customer cust);
	
	public Transactions depositAmount(Transactions trans);
	
	public Transactions withdrawAmount(Transactions trans);
	
	public List<Transactions> getTransactions(Long id);
	
}
