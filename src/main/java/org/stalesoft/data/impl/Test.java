package org.stalesoft.data.impl;

public class Test {

	
	interface MyGreeting {
		String processName(String str);
	}

	public static void main(String args[]) {
		MyGreeting morningGreeting = (str) -> "Good Morning " + str + "!";
		MyGreeting eveningGreeting = (str) -> "Good Evening " + str + "!";
	  
	  	// Output: Good Morning Luis! 
		System.out.println(morningGreeting.processName("Luis"));
		
		// Output: Good Evening Jessica!
		System.out.println(eveningGreeting.processName("Jessica"));	
	}
}
