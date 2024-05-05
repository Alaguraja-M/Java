package com.pack.FarmToMarket.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
 
@Entity
@Table(name="equipment")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Equipment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//change
	@Column(name="equipment_id")
	private Integer id;
	private String name;
	private Integer count;
	private Double rentPerDay;
	private String state;
	private String city;
	private String village;
	private String pincode;
	private String contactPerson;
	private String mobileNumber;
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer equipmentId) {
		this.id = equipmentId;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public Integer getCount() {
		return count;
	}
 
	public void setCount(Integer count) {
		this.count = count;
	}
 
	public Double getRentPerDay() {
		return rentPerDay;
	}
 
	public void setRentPerDay(Double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}
 
	public String getState() {
		return state;
	}
 
	public void setState(String state) {
		this.state = state;
	}
 
	public String getCity() {
		return city;
	}
 
	public void setCity(String city) {
		this.city = city;
	}
 
	public String getVillage() {
		return village;
	}
 
	public void setVillage(String village) {
		this.village = village;
	}
 
	public String getPincode() {
		return pincode;
	}
 
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
 
	public String getContactPerson() {
		return contactPerson;
	}
 
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
 
	public String getMobileNumber() {
		return mobileNumber;
	}
 
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
 
	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Equipment(Integer equipmentId, String name, Integer count, Double rentPerDay, String state, String city,
			String village, String pincode, String contactPerson, String mobileNumber, User user) {
		super();
		this.id = equipmentId;
		this.name = name;
		this.count = count;
		this.rentPerDay = rentPerDay;
		this.state = state;
		this.city = city;
		this.village = village;
		this.pincode = pincode;
		this.contactPerson = contactPerson;
		this.mobileNumber = mobileNumber;
		this.user = user;
	}
 
	public Equipment(String name, Integer count, Double rentPerDay, String eState, String eCity, String village,
			String pincode, String contactPerson, String eMobileNumber, User user) {
		super();
		this.name = name;
		this.count = count;
		this.rentPerDay = rentPerDay;
		this.state = eState;
		this.city = eCity;
		this.village = village;
		this.pincode = pincode;
		this.contactPerson = contactPerson;
		this.mobileNumber = eMobileNumber;
		this.user = user;
	}	
}