package com.example.setu.pojo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

public class Transaction {

	@NotNull
	@NotEmpty(message = "transaction id should have some value")
	String id;
	
	@NonNull
	@NotEmpty(message = "Should Valid amount")
	String amountPaid;
	
	@NonNull
	@NotBlank(message = "Should have Valid date")
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
