package com.pack.java8;

import java.util.*;
import java.util.stream.*;

public class StreamsOther {

	public static void main(String[] args) {
		 Stream<String> s1=Stream.<String>builder().add("GK").add("BK").add("JK").build();
	        s1.forEach(System.out::println);
	        
	        Stream<String> s2=Stream.generate(()->"hello").limit(5);
	        s2.forEach(System.out::println);
	        
	        Stream<Integer> s3=Stream.iterate(2,(i)->i*2).skip(3).limit(5);
	        s3.forEach(System.out::println);
	        
	        IntStream i1=IntStream.range(1, 6); //start to end-1
	        i1.forEach(System.out::println); //1,2,3,4,5
	        
	        IntStream i2=IntStream.rangeClosed(1,6); //start to end
	        i2.forEach(System.out::println); //1,2,3,4,5,6
	        
	        IntStream i3="abcd".chars();
	        i3.forEach(System.out::println); //97 98 99 100
	        
	        Random r=new Random();
	        DoubleStream d=r.doubles(5);
	        d.forEach(System.out::println);
	        
			/*
			 * List<Student> l1=new ArrayList<>(); l1.add(new Student("PK",23)); l1.add(new
			 * Student("KK",26)); l1.add(new Student("MK",23)); l1.add(new
			 * Student("SK",21)); l1.add(new Student("RK",40)); l1.add(new
			 * Student("BK",30)); l1.add(new Student("DK",29)); l1.add(new
			 * Student("GK",28)); l1.add(new Student("TK",33));
			 * 
			 * IntStream i4=l1.stream().mapToInt(Student::getAge);
			 * i4.forEach(System.out::println);
			 * 
			 * OptionalInt opt=l1.stream().mapToInt(Student::getAge).max();
			 * System.out.println(opt.getAsInt()); //40
			 * 
			 * OptionalDouble op1=l1.stream().mapToDouble(Student::getAge).average();
			 * System.out.println(op1.getAsDouble());
			 */
	        
	        //IntSummaryStatistics,DoubleSummaryStatistics,LongSummaryStatistics
			/*
			 * IntSummaryStatistics
			 * i5=l1.stream().collect(Collectors.summarizingInt(t->t.getAge()));
			 * System.out.println(i5); System.out.println(i5.getCount()+" "+i5.getSum());
			 */
	        
	        Stream.of(2,4,6,8,9,10,12).takeWhile(n->n%2==0)
	            .forEach(System.out::println); //2,4,6,8
	        Stream.of(2,4,6,8,9,10,12).dropWhile(n->n%2==0)
	            .forEach(System.out::println); //9,10,12

	}

}
