package com.example.setu.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class FetchBillRequestParameter {

	@NotNull
	@NotEmpty(message = "Contact no. should not be empty")
	@Size(min = 10, max = 10, message = "Contact no. is not valid")
	@Pattern(regexp = "^[0-9]*$", message = "Invalid  format")
	String mobileNumber;

	public FetchBillRequestParameter() {

	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
