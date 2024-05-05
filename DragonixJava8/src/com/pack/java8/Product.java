package com.pack.java8;

public class Product {
	private Long id;
	private String name, category;
	private Double price;
	
	public Product(Long idLong, String nameString, String categoryString, Double priceDouble) {
		// TODO Auto-generated constructor stub
		this.id = idLong;
		this.name = nameString;
		this.category = categoryString;
		this.price = priceDouble;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}

