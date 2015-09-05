package dev.practice.drools;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the freedom database table.
 * 
 */
@Entity
@NamedQuery(name = "Freedom.findAll", query = "SELECT f FROM Freedom f")
public class Freedom implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private FreedomPK         id;

  private BigDecimal        date;

  private String            state;

  public Freedom() {
  }

  public FreedomPK getId() {
    return this.id;
  }

  public void setId(FreedomPK id) {
    this.id = id;
  }

  public BigDecimal getDate() {
    return this.date;
  }

  public void setDate(BigDecimal date) {
    this.date = date;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

}