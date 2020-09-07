package com.application.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransJPA  extends JpaRepository<Transactions, Integer>{

	List<Transactions> findByAccno(Long accno);
	
	

}
