package com.pack.democodes;

public class Student implements Comparable<Student> {
	private Integer id;
	private String name;
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Student(Integer id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Student o) {
		if (age == o.age)
			return 0;
		else if (age > o.age)
			return 1;
		else
			return -1;
	}
	/*
	 * @Override public int compareTo(Student o) { return name.compareTo(o.name); }
	 */

}