package com.application.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.application.demo.config.JwtTokenUtil;
import com.application.demo.model.Customer;
import com.application.demo.model.JwtRequest;
import com.application.demo.model.JwtResponse;
import com.application.demo.model.Transactions;
import com.application.demo.service.BankSeriveImpl;
import com.application.demo.service.JwtUserDetailsService;

@RestController
public class ApplicationController {

	@Autowired
	BankSeriveImpl service;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	
	@PostMapping(value = "/save")
	public ResponseEntity<Customer> createAccount(@RequestBody Customer customer) {
		 
		 Customer	result = service.createAccount(customer);
		 if(result != null) {
		 return new ResponseEntity<>(result, HttpStatus.CREATED);}
		 else
		 {
			 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
		       service.removeAccount(id);
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
				 return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
