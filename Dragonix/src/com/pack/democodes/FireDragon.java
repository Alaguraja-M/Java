package com.pack.democodes;

import java.util.Scanner;

public class FireDragon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Feed the Fire Dragon:");
		String c = sc.next();
		switch (c) {
		case "F":
			System.out.println("Fire Dragon is starving for more food");
			break;
		default:
			System.out.println("Not enough meat for the Fire Dragon");
			break;
		}
		sc.close();
	}

}
