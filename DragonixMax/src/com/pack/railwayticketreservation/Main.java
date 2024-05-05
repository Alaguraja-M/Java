package com.pack.railwayticketreservation;

//import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		RailwayReservationSystem reservationSystem = new RailwayReservationSystem();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\nRailway Ticket Reservation System");
			System.out.println("1. Book Ticket");
			System.out.println("2. Cancel Ticket");
			System.out.println("3. Print Booked Tickets");
			System.out.println("4. Print Available Tickets");
			System.out.println("5. Exit");

			System.out.print("Enter your choice (1-5): ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline left by nextInt()
			System.out.println("-----------------------");

			switch (choice) {
			case 1:
				reservationSystem.bookTicket();
				break;
			case 2:
				reservationSystem.cancelTicket();
				break;
			case 3:
				reservationSystem.printBookedTickets();
				break;
			case 4:
				reservationSystem.printAvailableTickets();
				break;
			case 5:
				System.out.println("Exiting the application. Thank you!");
				System.exit(0);
//				return;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 5.");
			}
		}
	}

}
