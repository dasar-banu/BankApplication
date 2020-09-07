package com.application.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.demo.model.Customer;

@Repository
public interface CustomerJPA extends JpaRepository<Customer, Long>{

}
