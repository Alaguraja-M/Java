package com.pack.java8;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
	static List<Product> productMainList; 
	static List <Product> product1List;
	static List<Product> product2List;
	static List<Product> product3List; 
	static List<Customer> customerList;
	static List<Order> orderList;
	
	static {
		productMainList = Arrays.asList(
				new Product(1L, "Car","Toys", 150.99),
				new Product(2L, "Radio","Toys", 35.56),
				new Product(3L, "water weapon","Toys", 200.00),
				new Product(4L, "sophie's world", "Books", 50.50),
				new Product(5L, "Afterparties","Books", 245.56),
				new Product(6L, "Calculus 2", "Books", 325.50),
				new Product(7L,"Huggies", "Baby", 35.05),
				new Product(8L,"Monitoring System", "Baby", 120.00),
				new Product(9L,"Snotsucker Nasal Aspirator", "Baby", 205.05)
				);
		
		product1List = Arrays.asList(
				productMainList.get(0),
				productMainList.get(3),
				productMainList.get(6)
				);
		
		product2List = Arrays.asList(
				productMainList.get(1),
				productMainList.get(4),
				productMainList.get(7)
				);
		
		product3List = Arrays.asList(
				productMainList.get(2),
				productMainList.get(5),
				productMainList.get(8)
				);
		
		customerList = Arrays.asList(
				new Customer(1L,"Ivar",1),
				new Customer(2L,"Cesar",2),
				new Customer(3L,"Jose",3),
				new Customer(4L,"Carlos",1)
				);
		
		orderList = Arrays.asList(
				new Order(1L, "Delivered" , LocalDate.of(2023, 4, 15),
						LocalDate.of(2023, 4, 17), customerList.get(0), product1List),
				
				new Order(2L, "Delivared", LocalDate.of(2023, 4, 20),
						LocalDate.of(2023, 4, 22), customerList.get(1), product2List),
				
				new Order(3L, "Delivared", LocalDate.of(2023, 4, 25),
						LocalDate.of(2023, 4, 30), customerList.get(2), product3List),
				
				new Order(4L, "At customs", LocalDate.of(2023, 5, 20),
						LocalDate.of(2023, 5, 30), customerList.get(2), product3List)
				);
	}

	public static void main(String[] args) {
		
		//Exercise 1: Obtain a list of products belongs to category “Books” with price > 100
		List<Product> bookList = 
			productMainList
				.stream()
				.filter((books)-> books.getCategory()=="Books" && books.getPrice() > 100)
				.collect(Collectors.toList());
		
		printResults(bookList, "1");
		
		 // Exercise 2: Obtain a list of order with products belong to category “Baby”
		List<Product> babyList = 
				productMainList
					.stream()
					.filter((babyCat)->babyCat.getCategory()=="Baby")
					.collect(Collectors.toList());
		
		printResults(babyList, "2");
		
		//Exercise 3: Obtain a list of product with category = “Toys” and then apply 10% discount
		List<Double> toyList = 
				productMainList
					.stream()
					.filter((toyCat)->toyCat.getCategory()=="Toys")
					.map((value)-> value.getPrice()-(value.getPrice()*.1))
					.collect(Collectors.toList());
		
		System.out.println("***** Exercise 3 *****");
		toyList.forEach(System.out::println);
		
		//Exercise 4: Get the cheapest products of “Books” category
		Optional<Product> cheapestBook= 
				productMainList
					.stream()
					.filter((toyCat)->toyCat.getCategory()=="Books")
					.sorted(Comparator.comparing(Product::getPrice))
					.findFirst();
		
		System.out.println("***** Exeise 4 *****");
		System.out.println(
				cheapestBook.get().getId()+" "
				+cheapestBook.get().getName()+" "
				+cheapestBook.get().getCategory()+" "
				+cheapestBook.get().getPrice()+"\n"
				);
		
		//Exercise 5: Get the 3 most recent placed order
		List<Order> mostRecentList = 
				orderList
				.stream()
				.sorted(Comparator.comparing(Order::getOrderDate))
				.limit(3)
				.collect(Collectors.toList());
		
		System.out.println("***** Exercise 5 *****");
		mostRecentList.forEach((product) -> System.out.println(
				product.getId()+" "
				+product.getStatus()+" "
				+product.getOrderDate()+" "
				+product.getDeliveryDate()+" "
				+product.getCustomer().getName()+" "
				+product.getProducts().get(0).getName()
				));
		
		 //Exercise 6: Obtain a collection of statistic figures (i.e. sum, average, max, min, count) for all products of category “Books”
		double sumPoductOptional = 
				productMainList
				.stream()
				.filter((cat) -> cat.getCategory() == "Books")
				.mapToDouble(Product::getPrice)
				.sum();
		
		OptionalDouble avgPoductOptional = 
				productMainList
				.stream()
				.filter((cat) -> cat.getCategory() == "Books")
				.mapToDouble(Product::getPrice)
				.average();
		
		OptionalDouble maxPoductOptional = 
				productMainList
				.stream()
				.filter((cat) -> cat.getCategory() == "Books")
				.mapToDouble(Product::getPrice)
				.max();
		
		OptionalDouble minPoductOptional = 
				productMainList
				.stream()
				.filter((cat) -> cat.getCategory() == "Books")
				.mapToDouble(Product::getPrice)
				.min();
		
		long countPoductOptional = 
				productMainList
				.stream()
				.filter((cat) -> cat.getCategory() == "Books")
				.mapToDouble(Product::getPrice)
				.count();
		
		System.out.println("***** Exercise 6 *****");
		System.out.println(sumPoductOptional);
		System.out.println(avgPoductOptional.getAsDouble());
		System.out.println(maxPoductOptional.getAsDouble());
		System.out.println(minPoductOptional.getAsDouble());
		System.out.println(countPoductOptional);
		
	}
	
	static void printResults(List<Product> lists, String str) {
		
		System.out.printf("***** Exercise %s *****\n", str);
		lists.forEach((product) -> System.out.println(
				+product.getId()+" "
				+product.getName()+" "
				+product.getCategory()+" "
				+product.getPrice()+"\n")
				);
	}

}
