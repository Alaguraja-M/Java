package com.pack.java8;

import java.util.*;
import java.util.stream.*;

public class StreamsCollectors {

	public static void main(String[] args) {
		       Integer a[]=new Integer[] {1,2,3,4,5};
		       List<Integer> l=Arrays.asList(a); 
		       //List<Integer> l1=List.of(1,2,3,4,5); //create immutable list, from JDK10
		       
		       List<Integer> l1=l.stream().map((e)->e*3).collect(Collectors.toList());
		       l1.forEach(System.out::println); //3,6,9,12,15
		       
		       List<Integer> l2=l.stream().flatMap((e1)->Stream.of(e1*2)).collect(Collectors.toList());
		       l2.forEach(System.out::println); //2,4,6,8,10
		       
		       String s1=Stream.of("lions","tigers","bears").collect(Collectors.joining("-"));
		       System.out.println(s1); //lions-tigers-bears
		       
		       long l3=Stream.of("lions","tigers","bears").collect(Collectors.counting());
		       System.out.println(l3); //3
		       
		       List<String> l4=Stream.of("lions","tigers","bears","toads","toads").filter((s)->s.startsWith("t")).collect(Collectors.toList());
		       l4.forEach(System.out::println); //tigers,toads,toads
		       
		       Set<String> s2=Stream.of("lions","tigers","bears","toads","toads").filter((s)->s.startsWith("t")).collect(Collectors.toSet());
		       s2.forEach(System.out::println); //tigers,toads
		       
		       TreeSet<String> ts=Stream.of("lions","tigers","bears","toads","toads","tadpole")
		       .filter((s)->s.startsWith("t"))
		       .collect(Collectors.toCollection(TreeSet::new));
		       ts.forEach(System.out::println); //tadpoles,tigers,toads
		       
		       Map<String,Integer> m1=Stream.of("lions","tigers","bears","toads")
		               .collect(Collectors.toMap(k1->k1, String::length));
		       m1.forEach((k,v)->System.out.println("Key = "+k+" Value = "+v));
		       
		       Map<Boolean,List<String>> m2=Stream.of("lions","tigers","bears","toads","toads","tadpoles")
		           .collect(Collectors.partitioningBy((a1)->a1.length()<=5));
		       System.out.println(m2);
		       
		       Map<Boolean,Set<String>> m3=Stream.of("lions","tigers","bears","toads","toads","tadpoles")
		               .collect(Collectors.partitioningBy((a1)->a1.length()<=5,Collectors.toSet()));
		           System.out.println(m3);
		           
		      Map<Integer,List<String>> m4=Stream.of("lions","tigers","bears","lions")
		           .collect(Collectors.groupingBy((e)->e.length()));
		      System.out.println(m4);
		      
		      Map<Integer,Set<String>> m5=Stream.of("lions","tigers","bears","lions","ape")
		              .collect(Collectors.groupingBy((e)->e.length(),Collectors.toSet()));
		         System.out.println(m5);
		         
		         TreeMap<Integer,Set<String>> m6=Stream.of("lions","tigers","bears","lions","ape")
		                 .collect(Collectors.groupingBy((e)->e.length(),TreeMap::new,Collectors.toSet()));
		            System.out.println(m6);


	}

}
