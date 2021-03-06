package com.application.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long accno;
	private double amount;
	private String type;
 	
	public Transactions() {
		
	}
	


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public long getAccno() {
		return accno;
	}
	public void setAccno(long accno) {
		this.accno = accno;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", accno=" + accno + ", amount=" + amount + ", type=" + type + "]";
	}
     
	
}
