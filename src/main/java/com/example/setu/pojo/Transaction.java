package com.example.setu.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@Validated
public class Transaction {

	@NotNull
	@NotEmpty(message = "transaction id should have some value")
	String id;
	
	@NotNull
	@NotEmpty(message = "Should Valid amount")
	String amountPaid;
	@NotNull
	@NotEmpty(message = "Should have Valid date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	String date;

	public Transaction() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
