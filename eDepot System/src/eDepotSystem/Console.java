package eDepotSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	private static final String filePath = "src/eDepotSystem/users.txt";
	private static Scanner S;
	private static Scanner s = null;
	private static String username;
	private static String password;
	private static Boolean isManager;
	private static boolean verified = false;
	private static String choice;
	private static String editedEmp;
	private static String newPass;
	private static Employee curEmployee = new Employee("", "", "", false);
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
		String addEmployee = Employee.getUsername() + " " + password + " " + Employee.getLocation() + " " + Employee.getIsManager();
		employees.add(addEmployee);
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
		
		try {
			s = new Scanner(new FileReader(filePath));
			
			while(s.hasNext() && !verified) {
				tempUsername = s.next();
				tempPassword = s.next();
				tempLocation = s.next();
				isManager = s.nextBoolean();
				
				//Statement to verify if credientials given match that in the users text file.
				if (tempUsername.equals(username) && tempPassword.equals(password) && tempLocation.equals(location) ) {
					System.out.println("Verified");
					verified = true;
					Employee.setUsername(username);
					Employee.setIsManager(isManager);
					Employee.setLocation(tempLocation);
					Employee.setPassword(tempPassword);
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
			System.out.printf("Hello %s, welcome to %s system \n",Employee.getUsername(), Employee.getLocation());
			System.out.println("Select Depot Location");
			System.out.println("1 - [V]iew Schedule");
			System.out.println("2 - [A]ccount Settings");
			//Manager menu
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
					Schedule.viewSchedule(Employee.getUsername());
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
					System.out.print("Location for employee(s): ");
					viewEmployees(S.next());
					break;
				}
				
				case "7" : {
					editEmployee();
				}
				
				case "Q" : {

					System.out.println(employees);
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
		Scanner sc = new Scanner(new FileReader(filePath));
		String username;
		String tempLocation;
		Boolean isManager;
		Boolean anyEmp = false;
		
		while(sc.hasNext()) {
			username = sc.next();
			sc.next();
			tempLocation = sc.next();
			isManager = sc.nextBoolean();
			
			//Prints out employees in current location
			if (tempLocation.equals(location) ) {
				System.out.printf("\nEmployee username: %s. Employee location: %s. Manager: %b \n", username, tempLocation, isManager);
				anyEmp = true;
			}
			
		}	
		if (anyEmp == false) {
			System.out.printf("Unfortunately no employees are currently in %s, sorry \n", location);
		}
		
		sc.close();
		return;
	}
	
	static void editEmployee() throws FileNotFoundException {
		System.out.println(employees);
		String tempEmp;
		String tempLoc;
		Boolean tempMan;
		Boolean done = false;
		Scanner s;
		s = new Scanner(new FileReader(filePath));
		System.out.println("~~~Employee Edit~~~ \n");
		System.out.print("Employee Username: ");
		editedEmp = S.next();
		
		while (s.hasNext()) {
			String pick;
			tempEmp = s.next();
			s.next();
			tempLoc = s.next();
			tempMan = s.nextBoolean();
			System.out.println(employees);
			
			if (editedEmp.equals(tempEmp)) {
				System.out.printf("Username: %s. Location %s. Manager? %s. \n", tempEmp, tempLoc, tempMan);
				System.out.print("Is this correct? Y/N \n");
				pick = S.next().toUpperCase();
				if (pick.equals("Y")) {
					System.out.print("New Username: ");
					String newUse = S.next();
					System.out.print("New Password: ");
					newPass = S.next();
					System.out.print("New Location: ");
					String newLoc = S.next();
					System.out.print("Is Manager?: ");
					Boolean newIsMan = S.nextBoolean();
					String addEmployee = newUse + " " + newPass + " " + newLoc + " " + newIsMan;
					employees.add(addEmployee);
					System.out.println(employees);
				}
				else if (pick.equals("N")) {
					System.out.println(employees);
				}
				else {
					System.out.println("Wrong input, returning to menu");
					System.out.println(employees);
				}
				
			}
		}
		if (done) {

			
		}
		s.close();
	}
	
	private static void loadData(String curUser) throws FileNotFoundException {
		String usernameB;
		String password;
		String location;
		Boolean isManager;
		// setup for seats hash map
		s = new Scanner(new FileReader(filePath));
		while (s.hasNext()) {				
			usernameB = s.next();
			password = s.next();
			location = s.next();
			isManager = s.nextBoolean();
			if (usernameB.equals(username)) {
				
			}
			else if(usernameB.equals(editedEmp)) {

			}
			else {
				String addEmployee = usernameB + " " + password + " " + location + " " + isManager;
				employees.add(addEmployee);
			}
		}
		

	}
	
	private static void saveData() throws Exception {
		// java's try-with-resource statement
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		String[] saveEmployees = employees.toArray(new String[employees.size()]);
		int i = 0;
		for (i = 0; i < saveEmployees.length; i++) {
			pw.print(saveEmployees[i] + " ");
		}
		pw.close();
	
}}
