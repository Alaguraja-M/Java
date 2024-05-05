package com.pack.java8;

import java.util.*;
import java.util.function.*;
//import java.util.function.Function;

public class predefinedfunctionalinterface {

	public static void main(String[] args) {
		List<String> l = new ArrayList<>();
		l.add("Ram");
		l.add("Sam");
		l.add("Raj");
		l.add("Tam");
		System.out.println(l); // [Ram,Sam,Raj,Tam]
		// l.forEach((e)->System.out.println(e)); //using lambda expr
		l.forEach(System.out::println); // method reference of instance method

		Map<Integer, String> hm = new HashMap<>();
		hm.put(3, "Ram");
		hm.put(1, "Dam");
		hm.put(10, "Tim");
		hm.put(4, "Jim");
		System.out.println(hm); // {random order}
		hm.forEach((k, v) -> System.out.println("Key = " + k + " Value = " + v));

		
		  Function<String, Integer> f = (e) -> e.length();
		  System.out.println(f.apply("Hello")); // 5
		  
		  BiFunction<Integer, Integer, Integer> bf = (x1, x2) -> x1 + x2;
		  System.out.println(bf.apply(10, 20)); // 30
		  
		  Predicate<String> p = (x) -> x.startsWith("Foo");
		  System.out.println(p.test("Foobar")); // true
		  
		  BiPredicate<Integer, String> bp = (n1, n2) -> (n1 > 20) &&
		  (n2.startsWith("Foo")); 
		  System.out.println(bp.test(25, "Foobar")); // true
		  
		  Supplier<String> su = () -> "hello"; System.out.println(su.get()); // hello
		  
		  BooleanSupplier bs = () -> 10 > 20; System.out.println(bs.getAsBoolean()); //false
		  
		  BinaryOperator<String> bo = (b1, b2) -> b1.concat(b2);
		  System.out.println(bo.apply("hello", "world")); // helloworld
		 
	}

}
