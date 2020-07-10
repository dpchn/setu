package com.example.setu.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AddPaymentInfoRequestParameter {

	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String contactNo;
	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String dueAmount;
	@NotNull
	@NotEmpty(message = "dueDate should not be empty")
	String dueDate;

	public AddPaymentInfoRequestParameter() {

	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
