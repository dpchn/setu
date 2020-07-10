package com.example.setu.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class SetuBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OrderBy(clause = "id ASC")
	private long id;

	@CreationTimestamp
	private Date created;

	@UpdateTimestamp
	private Date modified;

	@Column(columnDefinition = "tinyint(1) default 0", nullable = false)
	private Boolean isDeleted;

	
	public SetuBase() {
		this.setCreated(new Timestamp(new Date().getTime()));
		this.setModified(new Timestamp(new Date().getTime()));
		this.setIsDeleted(false);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
