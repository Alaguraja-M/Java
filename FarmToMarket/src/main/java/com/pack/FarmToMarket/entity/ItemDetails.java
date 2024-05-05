package com.pack.FarmToMarket.entity;
 
public class ItemDetails {
    private Integer itemId;
    private String itemName;
    private Integer itemQuantity;
    private String username;
    private String mobileNumber;
    private Integer id;
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
	public Integer getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(Integer itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ItemDetails(Integer itemId, String itemName, Integer itemQuantity, String username, String mobileNumber,
			Integer id) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.id = id;
	}
	public ItemDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

}