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
import java.util.Map;
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
	private static String choice;
	//Save employees in array to load and save data
	private static List<String> employees = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
		S = new Scanner(System.in);
		choice = "";
		
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
		loadData(username);
		saveData();
		
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
		Boolean tempisManager = false;
		
		try {
			s = new Scanner(new FileReader(filePath));
			
			while(s.hasNext() && !verified) {
				tempUsername = s.next();
				tempPassword = s.next();
				tempLocation = s.next();
				tempisManager = s.nextBoolean();
				
				//Statement to verify if credientials given match that in the users text file.
				if (tempUsername.equals(username) && tempPassword.equals(password) && tempLocation.equals(location) ) {
					System.out.println("Verified");
					verified = true;
					Employee curEmployee = new Employee(username, password, location, tempisManager);
					mainMenu();					
				}
				else if (!verified && !s.hasNext()) {
					System.out.println("Wrong login credentails");
				}
			}
			
		}
		catch(Exception e) {
			
		}
		s.close();
		
	}
	
	private static void mainMenu() throws Exception {
		do {
			choice = "";
			
			System.out.println("~~~ Main Menu ~~~");		
			System.out.printf("Hello %s, welcome to %s system \n", Employee.getUsername(), Employee.getLocation());
			System.out.println("Select Depot Location");
			System.out.println("1 - [V]iew Schedule");
			System.out.println("2 - [A]ccount Settings");
			if (Employee.getIsManager() == true) {
				System.out.println("3 - [C]reate Schedule");
				System.out.println("4 - [E]dit Vehicle");
				System.out.println("5 - [Ad]d Employee");	
				System.out.println("6 - [Vi]ew Employee");
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
				case "A" : {
					accountSettings();
					break;
				}
				case "3" :
				case "C" : {
					//Create Schedule
					
				}
				case "4" :
				case "E" : {
					//Edit Vehicle;
					break;
				}
				
				case "5" :
				case "AD" : {
					createEmployee();
					break;
				}
				
				case "6" :
				case "VI" : {
					viewEmployees(Employee.getLocation());
					break;
				}
				
				case "Q" : {
					String addEmployee = Employee.getUsername() + " " + password + " " + Employee.getLocation() + " " + Employee.getIsManager();
					employees.add(addEmployee);
					break;
				}

			}
		} while (!choice.equals("Q"));
		//saveData();

	}
	
	private static void accountSettings() {
		System.out.printf("%s's Account Settings \n", Employee.getUsername());
		System.out.println("1 - Edit Username");
		System.out.println("2 - Edit Password");
		System.out.println("3 - Quit");
		choice = S.next();
		
		switch (choice) {
			case "1" : {
				System.out.print("New Username: ");
				Employee.setUsername(S.next());;
				break;
			}
			case "2" : {
				System.out.print("New Password: ");
				Employee.setPassword(S.next());;
				break;
			}
			case "3" :
			case "Q" : {
				//Create Schedule
				break;
			}
		}
		
		
	}

	private static void createSchedule() {
		
	}
	
	private static void editVehicle() {
		
	}
	
	private static void createEmployee() throws FileNotFoundException {
		System.out.print("Username:");
		String username = S.next();
		System.out.print("Password:");
		String password = S.next();
		System.out.print("Location:");
		String location = S.next();
		System.out.print("Manager? (true/false only)");
		Boolean isManager = S.nextBoolean();
		String addEmployee = username + " " + password + " " + location + " " + isManager;
		employees.add(addEmployee);
		
	}
	
	static void viewEmployees(String location) throws FileNotFoundException {
		S = new Scanner(new FileReader(filePath));
		String username;
		String tempLocation;
		Boolean isManager;
		
		while(S.hasNext()) {
			username = S.next();
			S.next();
			tempLocation = S.next();
			isManager = S.nextBoolean();
			
			if (tempLocation.equals(location) ) {
				System.out.printf("\nEmployee username: %s. Employee location: %s. Manager: %b \n", username, location, isManager);
			}

		}		
		
		S.close();
		
	}
	
	private static void loadData(String curUser) throws FileNotFoundException {
		String username;
		String password;
		String location;
		Boolean isManager;
		// setup for seats hash map
		s = new Scanner(new FileReader(filePath));
		while (s.hasNext()) {				
			username = s.next();
			password = s.next();
			location = s.next();
			isManager = s.nextBoolean();
			if (username.equals(curUser)) {
				
			}
			else {
				String addEmployee = username + " " + password + " " + location + " " + isManager;
				employees.add(addEmployee);
			}					
		}
		

	}
	
	private static void saveData() throws Exception {
		// java's try-with-resource statement
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		String[] saveEmployees = employees.toArray(new String[employees.size()]);
		int count = 0;
		int i = 0;
		for (i = 0; i < saveEmployees.length; i++) {
			pw.print(saveEmployees[i] + " ");
			count++;
			if (count == 4) {
				pw.print("\n");
			}
		}
		pw.close();
	
}}
