package com.practice.drools.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the validation_mess database table.
 * 
 */
@Entity
@Table(name="validation_mess")
@NamedQuery(name="ValidationMess.findAll", query="SELECT v FROM ValidationMess v")
public class ValidationMess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="VALIDATION_NO")
	private int validationNo;

	@Column(name="VALIDATION_MSG")
	private String validationMsg;

	public ValidationMess() {
	}

	public int getValidationNo() {
		return this.validationNo;
	}

	public void setValidationNo(int validationNo) {
		this.validationNo = validationNo;
	}

	public String getValidationMsg() {
		return this.validationMsg;
	}

	public void setValidationMsg(String validationMsg) {
		this.validationMsg = validationMsg;
	}

}