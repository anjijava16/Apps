package com.practice.drools;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the freedom database table.
 * 
 */
@Embeddable
public class FreedomPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String country;

	private String continent;

	public FreedomPK() {
	}
	public String getCountry() {
		return this.country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContinent() {
		return this.continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FreedomPK)) {
			return false;
		}
		FreedomPK castOther = (FreedomPK)other;
		return 
			this.country.equals(castOther.country)
			&& this.continent.equals(castOther.continent);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.country.hashCode();
		hash = hash * prime + this.continent.hashCode();
		
		return hash;
	}
}