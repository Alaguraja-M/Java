package com.pack.FarmToMarket.entity;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
 
@Entity
@Table
public class EquipmentUser {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Integer id;
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   @ManyToOne
   @JoinColumn(name = "equipment_id")
   private Equipment equipment;
   private Integer quantity;
 
public Integer getId() {
	return id;
}
 
public void setId(Integer id) {
	this.id = id;
}
 
public User getUser() {
	return user;
}
 
public void setUser(User user) {
	this.user = user;
}
 
public Equipment getEquipment() {
	return equipment;
}
 
public void setEquipment(Equipment equipment) {
	this.equipment = equipment;
}
 
public Integer getQuantity() {
	return quantity;
}
 
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
 
public EquipmentUser(Integer id, User user, Equipment equipment, Integer quantity) {
	super();
	this.id = id;
	this.user = user;
	this.equipment = equipment;
	this.quantity = quantity;
}
 
public EquipmentUser() {
	super();
	// TODO Auto-generated constructor stub
}
 
public EquipmentUser(User user, Equipment equipment, Integer quantity) {
	super();
	this.user = user;
	this.equipment = equipment;
	this.quantity = quantity;
}

}