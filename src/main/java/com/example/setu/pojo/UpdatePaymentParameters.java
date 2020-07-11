package com.example.setu.pojo;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.NonNull;


public class UpdatePaymentParameters {

	@NotNull
	@NotEmpty(message = "refId should not empty")
	String refId;

	@NonNull
	@Valid
	Transaction transaction;

	public UpdatePaymentParameters() {

	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
