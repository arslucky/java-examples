package org.ars.example.function;

import java.util.function.Function;

class Function2 {
    //int size() { System.out.println("1"); return 0; }
    static int size(Function2 arg) { System.out.println("2"); return 0; }
    int size(Integer arg) { System.out.println("3"); return 0; }
    
    void test() {
    	Function2 c = new Function2();
    	//Function<C, Integer> f1 = (a) -> {return a.size(1);};
        Function<Function2, Integer> f1 = Function2::size;
          // Error: instance method size()
          // or static method size(Object)?
        f1.apply(this);
        //f1.apply(1);
    }
    
    public static void main(String[] args) {
		try {
			Function2 c = new Function2();
			c.test();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
