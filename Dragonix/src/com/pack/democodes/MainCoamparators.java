package com.pack.democodes;

import java.util.*;

public class MainCoamparators {

	public static void main(String[] args) {
		List<Player> l2 = new ArrayList<>();
		l2.add(new Player("Ram", "India", "CSE"));
		l2.add(new Player("Raj", "Russia", "IT"));
		l2.add(new Player("Sam", "Asia", "Mech"));
		l2.add(new Player("Abu", "Europe", "EEE"));

		Collections.sort(l2, new NameComparator()); // internally calls compare()
		for (Player p1 : l2)
			System.out.println(p1.getName());

		System.out.println();
		Collections.sort(l2, new CountryComparator()); // internally calls compare()
		for (Player p1 : l2)
			System.out.println(p1.getCountry());

		System.out.println();
//		TreeSet<Player> ts2 = new TreeSet<>(new CountryComparator());
		TreeSet<Player> ts2 = new TreeSet<>(new NameComparator());
		ts2.add(new Player("Ram", "India", "CSE"));
		ts2.add(new Player("Raj", "Russia", "IT"));
		ts2.add(new Player("Sam", "Asia", "Mech"));
		ts2.add(new Player("Abu", "Europe", "EEE"));
		for (Player p1 : ts2)
			System.out.println(p1.getCountry() + " " + p1.getSkill());

	}

}
