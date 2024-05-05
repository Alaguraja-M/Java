package com.pack.democodes;

import java.util.Scanner;

class NotValidAgeException extends Exception {
	String s1 = "";

	public NotValidAgeException(String s1) {
		super();
		this.s1 = s1;
	}

	@Override
	public String toString() {
		return s1;
	}

}

public class Exp {
	/*
	 * static void demo() { try { throw new NullPointerException("Demo"); } catch
	 * (NullPointerException e) { System.out.println("Caught"); //throw e; } }
	 * 
	 * public static void main(String... args) { try { demo(); } catch
	 * (NullPointerException e) { System.out.println("Recaught"); }
	 * 
	 * }
	 */

	/*
	 * static void demoA() { try { System.out.println("Inside demoA"); throw new
	 * RuntimeException("Hello"); } finally {
	 * System.out.println("Inside demoA finally"); } }
	 * 
	 * static void demoB() { try { System.out.println("Inside demoB"); return; }
	 * finally {
	 * 
	 * System.out.println("DemoB finally"); } }
	 * 
	 * public static void main(String[] args) { try { demoA(); } catch
	 * (RuntimeException e) { System.out.println("caught"); } demoB(); }
	 */
	static void validateAge() throws NotValidAgeException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter age");
		int age = sc.nextInt();
		if (age < 18) {
			throw new NotValidAgeException("Your age is not eligible");
		} else {
			System.out.println("You are eligible");
		}
		sc.close();
	}

	public static void main(String[] args) {
		try {
			validateAge();
		} catch (NotValidAgeException e) {
			System.out.println(e);
		}
	}

}
