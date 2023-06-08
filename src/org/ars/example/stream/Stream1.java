package org.ars.example.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Stream1 {

	public static void main(String[] args) {
		try {
			List<Integer> list = new ArrayList<>();
			
			list.add(1);list.add(2);list.add(3);list.add(4);
			
			List<Integer> subList = list.subList(1, 2);
						
			List<Integer> list2 = new ArrayList<>( list);
			
			//subList.clear();
			//list.add(5); //java.util.ConcurrentModificationException
			
			System.out.println( list);
			System.out.println( subList); //java.util.ConcurrentModificationException
			System.out.println( list2);
			
			list.forEach( i -> {System.out.println(i + ",");});
			//------------------------------//
			Collection<Integer> col = new HashSet<>();
			
			col.add(1);col.add(2);col.add(3);col.add(4);
			
			int[] arr = col.stream().mapToInt( x -> x*2).toArray();
			
			for( int i=0; i<arr.length; i++) {
				System.out.print( arr[i] + ",");
			}
			System.out.println("");
			//------------------------------//
			List<String> l = new ArrayList<>();
			list.clear();
			
			l.add("one");l.add("two");l.add("three");l.add("four");l.add("one");l.add("two");
			list.add(1);list.add(2);list.add(3);
			
			Optional<String> r = l.stream().sorted().reduce( (a,b) -> a.concat("," + b));
			Optional<String> r2 = l.stream().reduce( String::concat);
			String r3 = l.parallelStream().reduce( "1", (a,b) -> a.concat(b), (a,b) -> {System.out.println("a="+a+" b="+b); return a.concat(b);});
			
			Set<String> set = l.stream().collect( Collectors.toSet());
			//Map<String, String> map = l.stream().collect( Collectors.toMap( e -> e.toString(), e -> e.toString().concat("-")));
			Map<String, List<String>> map1 = l.stream().collect( Collectors.groupingBy( e -> e.toString()));
			Map<String, Long> map2 = l.stream().collect( Collectors.groupingBy( e -> e.toString(), Collectors.counting()));
			
			System.out.println( r.get());
			System.out.println( r2.get());
			System.out.println( r3);
			System.out.println( set);
			//System.out.println( map);
			System.out.println( map1);
			System.out.println( map2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
