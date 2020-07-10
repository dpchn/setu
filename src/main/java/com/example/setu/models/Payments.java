package com.example.setu.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Payments extends SetuBase {

	@Column
	private double dueAmount;

	@Column(nullable = false)
	String dueDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Column(nullable = true)
	private String refId;

	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private boolean isPaid;

	@Column
	private String paidOn;

	@Column
	private String ackId;

	public Payments() {
		super();
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getPaidOn() {
		return paidOn;
	}

	public void setPaidOn(String paidOn) {
		this.paidOn = paidOn;
	}

	public String getAckId() {
		return ackId;
	}

	public void setAckId(String ackId) {
		this.ackId = ackId;
	}
}
