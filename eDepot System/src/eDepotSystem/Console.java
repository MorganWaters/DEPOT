package eDepotSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.bind.DatatypeConverter;

public class Console {
	private static final String filePath = "src/eDepotSystem/users.txt";
	private static Scanner S;
	private static Scanner s = null;
	private static String username;
	private static String password;
	private static boolean verified = false;
	private static boolean isManager = false;
	private static String choice;

	public static void main(String[] args) throws Exception {
		S = new Scanner(System.in);
		
		
		do {
			choice = "";
			System.out.println("Select Depot Location");
			System.out.println("1 - [L]iverpool");
			System.out.println("2 - [M]anchester");
			System.out.println("Q - Quit");
			System.out.print("Pick: ");
			
			choice = S.next().toUpperCase();
			
			
			switch (choice) {
				case "1" :
				case "L" : {
					login("Liverpool");
					break;
				}
				case "2" :
				case "M" : {
					login("Manchester");
					break;
				}

			}
		
		} while (!choice.equals("Q") && !verified);	
		
		S.close();

	}
	
	private static void login(String location) {
		S = new Scanner(System.in);
		System.out.printf("Login to %s system \n", location);
		System.out.print("Username: ");
		username = S.next();
		System.out.print("Password: ");
		password = S.next();
		verifyLogin(username, password, location);
				
	}
	
	private static void verifyLogin(String username, String password, String location) {
		String tempUsername = "";
		String tempPassword = "";
		String tempLocation = "";
		
		try {
			s = new Scanner(new FileReader(filePath));
			
			while(s.hasNext() && !verified) {
				tempUsername = s.next();
				tempPassword = s.next();
				tempLocation = s.next();
				isManager = s.nextBoolean();
				
				if (tempUsername.equals(username) && tempPassword.equals(password) && tempLocation.equals(location) ) {
					System.out.println("Verified");
					verified = true;
					mainMenu(username, location);					
				}
				else {
					System.out.println("Not verified");
				}
			}
		}
		catch(Exception e) {
			
		}
		s.close();
		
	}
	
	private static void mainMenu(String username, String location) {
		do {
			choice = "";
			
			System.out.println("~~~ Main Menu ~~~");		
			System.out.printf("Hello %s, welcome to %s system \n", username, location);
			System.out.println("Select Depot Location");
			System.out.println("1 - [V]iew Schedule");
			if (isManager == true) {
				System.out.println("2 - [C]reate Schedule");
				System.out.println("3 - [E]dit Vehicle");
				System.out.println("4 - [A]dd employee");
				System.out.println("5 - [Ed]it employee");				
			}			
			System.out.println("Q - Quit");
			System.out.print("Pick: ");
			
			choice = S.next().toUpperCase();
			
			switch (choice) {
			case "1" :
			case "V" : {
				Schedule.viewSchedule(username);
				break;
			}
			case "2" :
			case "C" : {
				Schedule.createSchedule();
				break;
			}
			case "3" :
			case "E" : {
				
			}
			case "4" :
			case "A" : {
				Employee.createEmployee();
			}

		}
		} while (!choice.equals("Q"));

	}
	
	private static void createSchedule() {
		
	}
	
	private static void editVehicle() {
		
	}
	
	
}
