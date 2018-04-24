package eDepotSystem;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	private static final String filePath = "src/eDepotSystem/users.txt";
	private static final String vehicleFile = "src/eDepotSystem/vehicle.txt";
	private static final String workFile = "src/eDepotSystem/workschedule.txt";
	private static Scanner S;
	private static Scanner s = null;
	private static String username;
	private static String password;
	private static boolean verified = false;
	private static String choice;
	private static String editedEmp;
	private static String newPass;
	private static Employee curEmployee = new Employee("", "", "", false);
	private static Vehicle editVehicle = new Vehicle("", "", "", "", "", "");
	//Save employees in array to load and save data
	private static String[] arrayVehicles;
	private static List<String> employees = new ArrayList<String>();
	private static List<String> vehicles = new ArrayList<String>();
	private static List<String> schedule = new ArrayList<String>();
	private static String[] saveEmployees;
	private static String[] saveSchedule;

	public static void main(String[] args) {
		S = new Scanner(System.in);
		choice = "";
		try {
			loadData();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
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
		// Try - catch to save data
		try {
			saveData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		S.close();

	}
	
	private static void login(String location) {
		S = new Scanner(System.in);
		System.out.printf("Login to %s system \n", location);
		System.out.print("Username: ");
		username = S.next();
		System.out.print("Password: ");
		password = S.next();
		try {
			verifyLogin(username, password, location);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}				
	}
	
	private static void verifyLogin(String username, String password, String location) throws Exception {
		String tempUsername = "";
		String tempPassword = "";
		String tempLocation = "";
		Boolean tempIsMan = false;
		int i = 0;
		
		while (i < saveEmployees.length && !verified) {
			if (username.equals(saveEmployees[i]) && password.equals(saveEmployees[i + 1])) {				
				tempUsername = saveEmployees[i];
				tempPassword = saveEmployees[i+1];
				tempLocation = saveEmployees[i+2];
				String tempIsManS = saveEmployees[i+3];
				tempIsMan = Boolean.parseBoolean(tempIsManS);
				curEmployee.setPassword(tempPassword);
				curEmployee.setIsManager(tempIsMan);
				curEmployee.setLocation(tempLocation);
				curEmployee.setUsername(tempUsername);
				verified = true;
				mainMenu();
			}
			else {
				i++;
			}
		}		
	}
	
	private static void mainMenu() throws Exception {
		do {
			choice = "";
			
			System.out.println("~~~ Main Menu ~~~");		
			System.out.printf("Hello %s, welcome to %s system \n", curEmployee.getUsername(), curEmployee.getLocation());
			System.out.println("Select Depot Location");
			System.out.println("1 - [V]iew Schedule");
			System.out.println("2 - [A]ccount Settings");
			//Manager menu
			if (curEmployee.getIsManager() == true) {
				System.out.println("3 - [C]reate Schedule");
				System.out.println("4 - [E]dit Vehicle");
				System.out.println("5 - [Ad]d Employee");	
				System.out.println("6 - [Vi]ew Employee");
				System.out.println("7 - [Ed]it Employee");
			}			
			System.out.println("Q - Quit");
			System.out.print("Pick: ");
			
			choice = S.next().toUpperCase();
			
			switch (choice) {
				case "1" :
				case "V" : {
					viewSchedule();
					break;
				}
				case "2" :
				case "A" : {
					accountSettings();
					break;
				}
				case "3" :
				case "C" : {
					createSchedule();
					
				}
				case "4" :
				case "E" : {
					editVehicle();
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
				
				case "7" :
				case "ED" : {
					editEmployee();
				}
				
				case "Q" : {
					break;
				}

			}
		} while (!choice.equals("Q"));

	}
	
	private static void viewSchedule() {
		String tempReg;
		String tempClient;
		String tempLoc;
		String tempStartD;
		String tempStartT;
		String tempEndD;
		String tempEndT;
		String tempPending;
		int i = 0;
		for (i = 0; i < saveSchedule.length; i++) {
			if (curEmployee.getUsername().equals(saveSchedule[i])) {
				tempReg = saveSchedule[i + 1];
				tempClient = saveSchedule[i + 2];
				tempLoc = saveSchedule[i + 3];
				tempStartD = saveSchedule[i + 4];
				tempStartT = saveSchedule[i + 5];
				tempEndD = saveSchedule[i + 6];
				tempEndT = saveSchedule[i + 7];
				tempPending = saveSchedule[i + 8];
				System.out.printf("\nSchedule: Reg: %s Client: %s Location: %s Start Date: %s Start Time: %s End Date: %s End Time: %s Pending: %s\n", tempReg, tempClient, tempLoc, tempStartD, tempStartT, tempEndD, tempEndT, tempPending);
			}
		}
	}
	
	private static void accountSettings() {
		System.out.printf("%s's Account Settings \n", curEmployee.getUsername());
		System.out.println("1 - Edit Username");
		System.out.println("2 - Edit Password");
		System.out.println("3 - Quit");
		String choice = S.next();
		String tempPass = null;
		
		switch (choice) {
			case "1" : {
				//Sets current users username using the Employee class
				System.out.print("New Username: ");
				curEmployee.setUsername(S.next());;
				break;
			}
			case "2" : {
				//Sets current users password using the Employee class
				System.out.print("New Password: ");
				tempPass = S.next();
				break;
			}
			case "3" :
			case "Q" : {
				break;
			}
		}
		int i = 0;
		
		for (i = 0; i < saveEmployees.length; i++) {
			if (username.equals(saveEmployees[i])) {
				saveEmployees[i] = curEmployee.getUsername();
				saveEmployees[i + 1] = tempPass;
			}
		}
		
		
	}

	private static void createSchedule() {
		System.out.print("Drivers name: ");
		WorkSchedule.setDriver(S.next());
		System.out.print("Reg No: ");
		WorkSchedule.setVehicleReg(S.next());
		System.out.print("Client Name: ");
		WorkSchedule.setClientName(S.next());
		System.out.print("Destination: ");
		WorkSchedule.setLocation(S.next());
		System.out.print("Start Date(DDMMYY): ");
		WorkSchedule.setStartDate(S.next());
		System.out.print("Start Time(HHMM): ");
		WorkSchedule.setStartTime(S.next());
		System.out.print("End Date(DDMMYY): ");
		WorkSchedule.setEndDate(S.next());
		System.out.print("End Time(HHMM): ");
		WorkSchedule.setEndTime(S.next());
		int x = saveSchedule.length;
		saveSchedule[x] = "Pending";
		saveSchedule[x-1] = WorkSchedule.getEndTime();
		saveSchedule[x-2] = WorkSchedule.getEndDate();
		saveSchedule[x-3] = WorkSchedule.getStartTime();
		saveSchedule[x-4] = WorkSchedule.getStartDate();
		saveSchedule[x-5] = WorkSchedule.getLocation();
		saveSchedule[x-6] = WorkSchedule.getClientName();
		saveSchedule[x-7] = WorkSchedule.getVehicleReg();
		saveSchedule[x-8] = WorkSchedule.getDriver();
		
		
	}
	
	private static void editVehicle() {
		System.out.println("~~~Edit Vehicle~~~");
		System.out.print("Enter Vehicle Reg No: ");
		String regNo = S.next();
		String tempReg;
		String tempMake;
		String tempModel;
		String tempWeight;
		String tempLoc;
		String tempType;
		Boolean found = false;
		String option;
		int i = 0;
		while (i < arrayVehicles.length && !found) {
			if (regNo.equals(arrayVehicles[i])) {
				System.out.println(i);
				tempReg = arrayVehicles[i];
				tempMake = arrayVehicles[i + 1];
				tempModel = arrayVehicles[i + 2];
				tempWeight = arrayVehicles[i + 3];
				tempLoc = arrayVehicles[i + 4];
				tempType = arrayVehicles[i + 5];
				System.out.printf("\nReg No: %s\n Make: %s\n Model: %s\n Weight: %s\n Location: %s\n Type: %s\n", tempReg, tempMake, tempModel, tempWeight, tempLoc, tempType);
				System.out.print("Edit Vehicle? Y/N: ");
				option = S.next().toUpperCase();
					if (option.equals("Y")) {
						editVehicle.setRegNo(tempReg);
						editVehicle.setMake(tempMake);
						editVehicle.setModel(tempModel);
						editVehicle.setType(tempType);
						editVehicle.setWeight(tempWeight);
						System.out.printf("\nReg No: %s\n Make: %s\n Model: %s\n Weight: %s\n Type: %s\n", editVehicle.getRegNo(), editVehicle.getMake(), editVehicle.getModel(), editVehicle.getWeight(), editVehicle.getType());
						System.out.print("New Location: ");
						editVehicle.setLocation(S.next());
						arrayVehicles[i + 4] = editVehicle.getLocation();
						found = true;
					}
					else {
						
					}
				
			}
			else {
				i++;
			}
		}
		
	}
	
	private static void createEmployee() throws Exception {
		System.out.print("Username: ");
		String username = S.next();
		System.out.print("Password: ");
		String password = S.next();
		System.out.print("Location: ");
		String location = S.next();
		System.out.print("Manager? (true/false only): ");
		Boolean isManager = S.nextBoolean();
		String isManagerb = isManager.toString();
		int x = saveEmployees.length;
		saveEmployees[x-1] = isManagerb;
		saveEmployees[x-2] = location;
		saveEmployees[x-3] = password;
		saveEmployees[x-4] = username;
		
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
		String tempEmp;
		String tempLoc;
		Boolean tempMan;
		Boolean done = false;
		Scanner s;
		s = new Scanner(new FileReader(filePath));
		System.out.println("\n~~~Employee Edit~~~ ");
		System.out.print("Employee Username: ");
		editedEmp = S.next();
		
		while (s.hasNext()) {
			String pick;
			tempEmp = s.next();
			s.next();
			tempLoc = s.next();
			tempMan = s.nextBoolean();
			
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
				}
				else if (pick.equals("N")) {
				}
				else {
					System.out.println("Wrong input, returning to menu");
				}
				
			}
		}
		if (done) {

			
		}
		s.close();
	}
	
	private static void loadData() throws FileNotFoundException {
		//Loads employees into array
		s = new Scanner(new FileReader(filePath));
		while (s.hasNext()) {
			employees.add(s.next());
		}
		saveEmployees = employees.toArray(new String[employees.size() + 4]);
		s.close();
		
		//Loads vehicles into array
		s = new Scanner(new FileReader(vehicleFile));
		while (s.hasNext()) {				
				vehicles.add(s.next());
		}
		arrayVehicles = vehicles.toArray(new String[vehicles.size()]);
		s.close();
		
		s = new Scanner(new FileReader(workFile));
		while (s.hasNext()) {
			schedule.add(s.next());
		}
		saveSchedule = schedule.toArray(new String[schedule.size() + 9]);
		s.close();
		
	}
	
	private static void saveData() throws Exception {
		
		//Saves employees
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		int x = 0;
		int i = 0;
		for (i = 0; i < saveEmployees.length; i++) {
			x++;
			pw.print(saveEmployees[i] + " ");
			if (x == 4) {
				pw.print("\n");
				x = 0;
			}

		}
		x = 0;
		pw.close();
		//Saves Vehicles
		
		pw = new PrintWriter(new FileWriter(vehicleFile));
		for (i = 0; i < arrayVehicles.length; i++) {
			x++;
			pw.print(arrayVehicles[i] + " ");
			if (x == 6) {
				pw.print("\n");
				x = 0;
			}
		}
		pw.close();
		
		pw = new PrintWriter(new FileWriter(workFile));
		for (i = 0; i < saveSchedule.length; i++) {
			x++;
			pw.print(saveSchedule[i] + " ");
			if (x == 9) {
				pw.print("\n");
				x = 0;
			}
		}
		pw.close();
	
	}
}
