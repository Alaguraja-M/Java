package com.pack.java8;

public class Customer {
	private Long id;
	private String name;
	private Integer tier;
	
	public Customer(Long id, String name, Integer tier) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.tier = tier;
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

	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}
	
	
}
