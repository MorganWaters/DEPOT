package eDepotSystem;

import java.io.BufferedReader;
import java.io.FileReader;

public class Employee {
	
	static void viewEmployees() {(String username) {
		BufferedReader in = new BufferedReader(new FileReader("<employee.txt>"));
		String line;
                 while((line = in.readLine()) != null)
                {
                 System.out.println(line);
                }
                 in.close();
        	}
		
	}
	
	static void createEmployee() {
		
	}
	
	static void editEmployee() {
		
	}
}
