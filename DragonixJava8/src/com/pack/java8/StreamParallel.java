package com.pack.java8;

import java.util.*;
import java.util.stream.*;

public class StreamParallel {

	public static void main(String[] args) {
		//accessing the stream sequentially so only one thread is created
        /*Stream.of(1,2,3,4,5,6,7,8,9).forEach(n->{
            System.out.println(Thread.currentThread().getName()+" "+n);
        });*/
        
        //accessing the stream parallelly so multiple threads are created but not ordered
         Stream.of(1,2,3,4,5,6,7,8,9).parallel().forEach(n->{
                System.out.println(Thread.currentThread().getName()+" "+n);
         });




         Stream.of(1,2,3,4,5,6,7,8,9).parallel().forEachOrdered(n->{
                System.out.println(Thread.currentThread().getName()+" "+n);
         });
         
         List<Integer> l=List.of(1,2,3,4,5);
         l.stream().parallel().forEachOrdered(n->{
                System.out.println(Thread.currentThread().getName()+" "+n);
         });


	}

}
