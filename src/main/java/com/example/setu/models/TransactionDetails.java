package com.example.setu.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class TransactionDetails extends SetuBase{

	@Column(unique=true)
	String transactionId;
	
	@Column
	String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentId", nullable = false)
	private Payments payment;
	
	@Column
	private String ackId;
	
	public TransactionDetails() {
		
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Payments getPayment() {
		return payment;
	}

	public void setPayment(Payments payment) {
		this.payment = payment;
	}

	public String getAckId() {
		return ackId;
	}

	public void setAckId(String ackId) {
		this.ackId = ackId;
	}
}
