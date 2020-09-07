package com.application.demo;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

	@Autowired
	BankSeriveImpl service;

	@PostMapping(value = "/save")
	public ResponseEntity<Customer> createAccount(@RequestBody Customer customer) {
		 
		 Customer	result = service.createAccount(customer);
		 if(result != null) {
		 return new ResponseEntity<>(result, HttpStatus.CREATED);}
		 else
		 {
			 return new ResponseEntity<>(null, HttpStatus.valueOf("Accont aleady created"));
		 }
		 
		

	}
	
	@GetMapping(value ="/getcustomers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		
		try {
		List<Customer> custmore=service.getAllAccountDeatils();
		
		if(custmore.isEmpty())
		{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(custmore, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @PutMapping("/cutomerupdate/{id}")
	  public ResponseEntity<Customer> updateTutorial(@PathVariable("id") long id, @RequestBody Customer customer) {
		 
		 
		 Customer cust=service.updateAccount(id, customer);
		if(cust!=null) 
		 return new ResponseEntity<>(cust, HttpStatus.OK);
		else
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	 }
	 
	 @DeleteMapping("/removeaccount/{id}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
	    try {
	      String str=service.removeAccount(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PostMapping(value = "/deposit")
		public ResponseEntity<Transactions> depositAmoumt(@RequestBody Transactions transaction) {
			 try {
			       
				 Transactions result= service.depositAmount(transaction);
			    
			 return new ResponseEntity<>(result, HttpStatus.CREATED);
			 }
			 catch (Exception e) {
				 return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			

		}
	 
	 @PostMapping(value = "/withdraw")
		public ResponseEntity<Transactions> withDrawAmoumt(@RequestBody Transactions transaction) {
		
			       
				 Transactions result= service.withdrawAmount(transaction);
			    
				 if(result !=null) {
			 return new ResponseEntity<>(result, HttpStatus.CREATED);
			 }
				 else {
				 return new ResponseEntity<>(null, HttpStatus.valueOf("Insuffiecent balance"));
			}
			

		}
		
	 @GetMapping(value ="/gettransactions/{id}")
		public ResponseEntity<List<Transactions>> getTransactiond(@PathVariable("id") long id)
		{
			
			try {
			List<Transactions> trans=service.getTransactions(id);
			
			if(trans.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(trans, HttpStatus.OK);
			}
			catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	 
}
