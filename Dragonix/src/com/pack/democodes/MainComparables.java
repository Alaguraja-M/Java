package com.pack.democodes;

import java.util.*;

public class MainComparables {

	public static void main(String[] args) {
		/*
		 * TreeSet<String> ts = new TreeSet<>(); ts.add("E"); ts.add("H"); ts.add("A");
		 * ts.add("B"); System.out.println(ts); // [A,B,E,H]
		 * 
		 * List<Student> l1 = new ArrayList<>(); l1.add(new Student(103, "Ram", 24));
		 * l1.add(new Student(101, "Sam", 23)); l1.add(new Student(100, "Raj", 21));
		 * l1.add(new Student(105, "Ramu", 25)); l1.add(new Student(104, "Jam", 23));
		 * 
		 * ListIterator<Student> lt = l1.listIterator(); while(lt.hasNext()) { Student s
		 * = (Student)lt.next(); System.out.println(s.getName()); }
		 * 
		 * Collections.sort(l1); System.out.println();
		 * 
		 * ListIterator<Student> lt1 = l1.listIterator(); while(lt1.hasNext()) { Student
		 * s = (Student)lt1.next(); System.out.println(s.getName()); }
		 */
		
		List<Integer> l=new ArrayList<>();
        l.add(4);
        l.add(2);
        l.add(5);
        l.add(1);
        l.add(6);
        System.out.println(l); //[4,2,5,1,6]
        Collections.sort(l);
        System.out.println(l); //[1,2,4,5,6]
        Collections.sort(l, Comparator.reverseOrder());
        System.out.println(l); //[6,5,4,2,1]

		TreeSet<Student> ts1 = new TreeSet<>();
		ts1.add(new Student(103, "Ram", 24));
		ts1.add(new Student(101, "Sam", 23));
		ts1.add(new Student(100, "Raj", 23));
		ts1.add(new Student(105, "Ramu", 25));
		ts1.add(new Student(104, "Jam", 23));
		for (Student s1 : ts1)
			System.out.println(s1.getAge() + " " + s1.getName());

	}

}
