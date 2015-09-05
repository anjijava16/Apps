package dev.practice.drools.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the validation_control database table.
 * 
 */
@Entity
@Table(name = "validation_control")
@NamedQuery(name = "ValidationControl.findAll", query = "SELECT v FROM ValidationControl v")
public class ValidationControl implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "VALIDATION_NO")
  private int               validationNo;

  @Column(name = "CREATED_BY")
  private String            createdBy;

  @Column(name = "CREATED_DATE")
  private int               createdDate;

  @Column(name = "CREATED_TIME")
  private int               createdTime;

  public ValidationControl() {
  }

  public int getValidationNo() {
    return this.validationNo;
  }

  public void setValidationNo(int validationNo) {
    this.validationNo = validationNo;
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

}