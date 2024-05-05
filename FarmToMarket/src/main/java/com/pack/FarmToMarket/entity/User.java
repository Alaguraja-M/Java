package com.pack.FarmToMarket.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
 
@Entity
@Table(name="user200")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	private String mobileNumber;
	private String state;
	private String city;
	private String pincode;
	private String role;
	private String username;
	private String password;
 
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getMobileNumber() {
		return mobileNumber;
	}
 
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
 
	public String getPincode() {
		return pincode;
	}
 
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
 
	public String getRole() {
		return role;
	}
 
	public void setRole(String role) {
		this.role = role;
	}
 
	public String getUsername() {
		return username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public User(Integer id, String mobileNumber, String state, String city, String pincode, String role,
			String username, String password) {
		super();
		this.id = id;
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.role = role;
		this.username = username;
		this.password = password;
	}
 
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public User(String mobileNumber, String state, String city, String pincode, String role, String username,
			String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.role = role;
		this.username = username;
		this.password = password;
	}
}