package com.pack.FarmToMarket.entity;
 
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
@Table(name="item")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private Integer itemQuantity;
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	public Integer getItemId() {
		return itemId;
	}
 
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
 
	public String getItemName() {
		return itemName;
	}
 
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
 
	public String getItemDescription() {
		return itemDescription;
	}
 
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
 
	public Integer getItemQuantity() {
		return itemQuantity;
	}
 
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
 
	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	public Item(Integer itemId, String itemName, String itemDescription, Integer itemQuantity, User user) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemQuantity = itemQuantity;
		this.user = user;
	}
 
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public Item(String itemName, String itemDescription, Integer quantity, User user) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemQuantity = quantity;
		this.user = user;
	}
}