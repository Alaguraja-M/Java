package com.pack.java8;

import java.util.*;
import java.util.stream.*;

public class Streams {

	public static void main(String[] args) {
		List<String> l=new ArrayList<>();
		l.add("Ram");
		l.add("Sam");
		l.add("Raj");
		l.add("Tam");
		l.add("Tim");
		l.add("Ram");
		l.add("John");
		l.add("Amy");

		Stream<String> st=l.stream();
		Stream<String> st1=st.distinct();
		long c=st1.count();
		System.out.println(c); //7

		long c1=l.stream().distinct().count();
		System.out.println(c1); //7

		boolean b1=l.stream().distinct().anyMatch((e)->e.startsWith("R"));
		System.out.println(b1); //true

		boolean b2=l.stream().distinct().allMatch((e)->e.startsWith("R"));
		System.out.println(b2); //false

		boolean b3=l.stream().distinct().noneMatch((e)->e.startsWith("Z"));
		System.out.println(b3); //true

		/*
		 * List<Student> l1=new ArrayList<>(); l1.add(new Student("PK",23)); l1.add(new
		 * Student("KK",26)); l1.add(new Student("MK",23)); l1.add(new
		 * Student("SK",21)); l1.add(new Student("RK",40)); l1.add(new
		 * Student("BK",30)); l1.add(new Student("DK",29)); l1.add(new
		 * Student("GK",28)); l1.add(new Student("TK",33));
		 * 
		 * Stream<Student> st3=l1.stream().filter((s1)->s1.getAge()>25);
		 * st3.forEach(System.out::println);
		 */

		Optional opt=Stream.of(3,5,6).reduce((a,b)->a*b);
		System.out.println(opt.get()); //90

		Integer i=Stream.of(3,5,6).reduce(2, (a,b)->a*b);
		System.out.println(i); //180

//		Integer i2=l1.stream().reduce(0, (age, stud)->age+stud.getAge(),Integer::sum);
//		System.out.println(i2);

		Optional<String> opt1=Stream.of("lion","ape","tiger").min((c11,c21)->c11.length()-c21.length());
		System.out.println(opt1.get()); //ape

	}

}
