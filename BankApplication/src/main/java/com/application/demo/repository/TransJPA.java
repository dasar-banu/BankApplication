package com.application.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.demo.model.Transactions;

public interface TransJPA  extends JpaRepository<Transactions, Integer>{

	List<Transactions> findByAccno(Long accno);
	
	

}
