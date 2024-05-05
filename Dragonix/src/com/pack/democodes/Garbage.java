package com.pack.democodes;

public class Garbage {

	public static void main(String[] args) {
		/*String s1 = new String("Hello");
		s1 = null;*/
		Garbage g1 = new Garbage();
		Garbage g2 = new Garbage();
		//g=null;
		g1=g2;
		//System.gc();
		g1.finalize();
		System.gc();
		System.out.println("Hello world");

	}

	protected void finalize() {
		System.out.println("Finalize called");
	}

}
