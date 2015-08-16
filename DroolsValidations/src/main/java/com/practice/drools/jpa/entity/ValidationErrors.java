package com.practice.drools.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the validation_errors database table.
 * 
 */
@Entity
@Table(name="validation_errors")
@NamedQuery(name="ValidationError.findAll", query="SELECT v FROM ValidationErrors v")
public class ValidationErrors implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ERROR_SEQ_NBR")
	private int errorSeqNbr;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private int createdDate;

	@Column(name="CREATED_TIME")
	private int createdTime;

	@Column(name="ERROR_MSG")
	private String errorMsg;

	public ValidationErrors() {
	}

	public int getErrorSeqNbr() {
		return this.errorSeqNbr;
	}

	public void setErrorSeqNbr(int errorSeqNbr) {
		this.errorSeqNbr = errorSeqNbr;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(int createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(int createdTime) {
		this.createdTime = createdTime;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}