package com.application.demo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankSeriveImpl implements BankService {

	@Autowired
	CustomerJPA cuJpa;

	@Autowired
	TransJPA transJpa;

	@Override
	public Customer createAccount(Customer cust) {
		Customer customer = null;
		Optional<Customer> _customer = cuJpa.findById(cust.getAccno());
		boolean flag = _customer.isPresent();
		if (!flag) {
			customer = cuJpa.save(cust);
			Transactions trans = new Transactions();
			trans.setAccno(customer.getAccno());
			trans.setAmount(customer.getAmount());
			trans.setType("deposit");
			transJpa.save(trans);
		}
		return customer;
	}

	@Override
	public Customer updateAccount(Long id, Customer cust) {
		Optional<Customer> custData = cuJpa.findById(id);
		Customer customer = null;
		if (custData.isPresent()) {
			Customer _cust = custData.get();
			_cust.setAccphno(cust.getAccphno());
			_cust.setAccemail(cust.getAccemail());
			_cust.setAccgender(cust.getAccgender());
			customer = cuJpa.save(_cust);
		}

		return customer;
	}

	@Override
	public List<Customer> getAllAccountDeatils() {

		List<Customer> cust = cuJpa.findAll();
		return cust;
	}

	@Override
	public String removeAccount(Long acc) {

		cuJpa.deleteById(acc);

		return "Success";
	}

	@Override
	public Transactions depositAmount(Transactions trans) {
		Transactions transaction = null;
		Optional<Customer> cust = cuJpa.findById(trans.getAccno());
		if (cust.isPresent()) {
			Customer custData = cust.get();
			double amt = custData.getAmount();
			amt = amt + trans.getAmount();
			custData.setAmount(amt);
			cuJpa.save(custData);
			transaction = transJpa.save(trans);
		}
		return transaction;
	}

	@Override
	public Transactions withdrawAmount(Transactions trans) {

		Transactions transaction = null;
		Optional<Customer> cust = cuJpa.findById(trans.getAccno());
		if (cust.isPresent()) {
			Customer custData = cust.get();
			double amt = custData.getAmount();
			if (amt > trans.getAmount()) {
				amt = amt - trans.getAmount();
				custData.setAmount(amt);
				cuJpa.save(custData);
				transaction = transJpa.save(trans);
			}
		}

		
		
		return transaction;
	}

	@Override
	public List<Transactions> getTransactions(Long accno) {
		     List<Transactions> trans=transJpa.findByAccno(accno);
		return trans;
	}

	
	
}
