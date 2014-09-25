/**
 * 
 */
package com.itravel.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

/**
 * @author Home
 *
 */
@NamedQueries({ 
			 @NamedQuery( 
			 name = "findBusInfoList", 
			 query = "FROM Search"
			 //AS s LEFT JOIN User AS u where (s.id = u.userId)" 
		) 
	} 
)

/*@NamedNativeQueries({
	@NamedNativeQuery(
	name = "findStockByStockCodeNativeSQL",
	query = "select * from search_data s where s.id = :searchid",
        resultClass = Stock.class
	)
})
*/
@Entity
@Table(name="search_data")
public class Search implements Serializable {
	/*@OneToMany
	 @JoinColumn(name="userId")
	private User user;*/
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="search_date")
	private Date searchDate;
	
	@Column(name="travel_date")
	private Date journeyDate;
	
	@Column(name="return_date")
	private Date returnDate;
	
	@Column(name="origin")
	private String fromCity;
	
	@Column(name="destination")
	private String toCity;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public Date getJourneyDate() {
		return journeyDate;
	}
	public void setJourneyDate(Date journeyDate) {
		this.journeyDate = journeyDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
/*	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
		
	

}
