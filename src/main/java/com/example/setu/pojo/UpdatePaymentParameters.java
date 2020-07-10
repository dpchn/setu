package com.example.setu.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

public class UpdatePaymentParameters {

	@NotNull
	@NotEmpty(message = "refId should not empty")
	String refId;

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
