package com.pack.java8;

interface A {
	default void show() {
		System.out.println("Inside A's show");
	}
}

interface B {
	default void show() {
		System.out.println("Inside B's show");
	}
}

class Sample implements A, B {
	@Override
	public void show() {
		A.super.show();
		B.super.show();
	}

}

public class defaultstatic {

	public static void main(String[] args) {
		Sample s = new Sample();
		s.show();

	}

}
