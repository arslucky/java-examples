package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Stream2 {

	public static void main(String[] args) {
		try {
			Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
			List<Integer> listOfIntegers =
			    new ArrayList<>(Arrays.asList(intArray));
			
			List<Integer> serialStorage = new ArrayList<>();
			
			  System.out.println("Serial stream:"); 
			  
			  listOfIntegers.stream()
			  // Don't do this! It uses a stateful lambda expression. 
			  .map(e -> {serialStorage.add(e); return e; })
			  .forEachOrdered(e -> System.out.print(e + " ")); System.out.println("");
			  
			  serialStorage.stream().forEachOrdered(e -> System.out.print(e + " "));
			  System.out.println("");
			 

			System.out.println("Parallel stream:");
			//List<Integer> parallelStorage = Collections.synchronizedList( new ArrayList<>());
			List<Integer> parallelStorage = new ArrayList<>();
			//Set<Integer> parallelStorage = new TreeSet<>();
			
			listOfIntegers.parallelStream()
			    // Don't do this! It uses a stateful lambda expression.
			    .map(e -> { parallelStorage.add(e); return e; })
			    .forEachOrdered(e -> System.out.print(e + " "));

			System.out.println("");
			
			//Thread.sleep(1000L);
			
			for( int e : parallelStorage) {
				System.out.print(e + " ");
			}
			System.out.println("");
			
			parallelStorage
			    .stream()
			    .forEachOrdered(e -> System.out.print(e + " "));
			
			System.out.println("");
			
//			Object o1 = a -> a.get();
			
			//Object o2 = (Consumer<Test>) a -> {return a.get();};
			
			//((Test) o2).get();
			/*
			String s = "".trim();
			List<String> list = new ArrayList<>();
			
			list.replaceAll( ss -> ss.trim());
			
			list.replaceAll( String::trim);
			
			int[] array = IntStream.range(0,5).parallel().map(x -> x*2).toArray();
			for( int i=0; i<array.length; i++) {
				System.out.print( array[i] + ",");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
