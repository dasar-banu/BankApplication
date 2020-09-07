package com.application.demo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6156641793993873895L;
	@Id
	private long accno;
	private String accname;
	private long accphno;
	private String accemail;
	private String accgender;
	private double amount;
	private String acctype;

	public long getAccno() {
		return accno;
	}

	public void setAccno(long accno) {
		this.accno = accno;
	}

	public String getAccname() {
		return accname;
	}

	public void setAccname(String accname) {
		this.accname = accname;
	}

	public long getAccphno() {
		return accphno;
	}

	public void setAccphno(long accphno) {
		this.accphno = accphno;
	}

	public String getAccemail() {
		return accemail;
	}

	public void setAccemail(String accemail) {
		this.accemail = accemail;
	}

	public String getAccgender() {
		return accgender;
	}

	public void setAccgender(String accgender) {
		this.accgender = accgender;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAcctype() {
		return acctype;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

}
