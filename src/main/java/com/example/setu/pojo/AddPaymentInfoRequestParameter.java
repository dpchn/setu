package com.example.setu.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AddPaymentInfoRequestParameter {

	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String mobileNumber;
	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String dueAmount;
	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String dueDate;

	public AddPaymentInfoRequestParameter() {

	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(String dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
