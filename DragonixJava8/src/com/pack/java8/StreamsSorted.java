package com.pack.java8;

import java.util.*;
import java.util.stream.*;

public class StreamsSorted {

	public static void main(String[] args) {
		List<String> l = List.of("9", "A", "z", "1", "B", "4", "e", "f");

		List<String> l11 = l.stream().sorted().collect(Collectors.toList());
		l11.forEach(System.out::println);

		System.out.println();
		List<String> l2 = l.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		l2.forEach(System.out::println);

		/*
		 * List<Student> l1 = new ArrayList<>(); l1.add(new Student("PK", 23));
		 * l1.add(new Student("KK", 26)); l1.add(new Student("MK", 23)); l1.add(new
		 * Student("SK", 21)); l1.add(new Student("RK", 40)); l1.add(new Student("BK",
		 * 30)); l1.add(new Student("DK", 29)); l1.add(new Student("GK", 28));
		 * l1.add(new Student("TK", 33));
		 * 
		 * // comparaingInt(), comparaingDouble(), comparaingLong() List<Student> l4 =
		 * l1.stream().sorted(Comparator.comparingInt(Student::getAge)).collect(
		 * Collectors.toList()); l4.forEach(System.out::println); System.out.println();
		 * 
		 * List<Student> l5 =
		 * l1.stream().sorted(Comparator.comparingInt(Student::getAge).reversed())
		 * .collect(Collectors.toList()); l5.forEach(System.out::println);
		 * 
		 * System.out.println(); List<Student> l6 =
		 * l1.stream().sorted(Comparator.comparing(Student::getName)).collect(Collectors
		 * .toList()); l6.forEach(System.out::println);
		 * 
		 * System.out.println(); List<Student> l7 =
		 * l1.stream().sorted(Comparator.comparing(Student::getName).reversed())
		 * .collect(Collectors.toList()); l7.forEach(System.out::println);
		 */

	}

}
