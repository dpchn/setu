package com.example.setu.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AddUseRequestrParamter {
	@NotNull
	@NotEmpty(message = "Contact no. should not be empty")
	@Size(min = 10, max = 10, message = "Contact no. is not valid")
	@Pattern(regexp = "^[0-9]*$", message = "Invalid  format")
	String contactNo;

	@NotNull
	@NotEmpty(message = "Should not null or empty")
	String userName;

	public AddUseRequestrParamter() {

	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
