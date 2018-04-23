package eDepotSystem;

import java.util.Scanner;

public class Employee {
	private static String username;
	private static String password;
	private static String location;
	private static Boolean isManager;
	
	public Employee(String username, String password, String location, Boolean isManager) {
		this.username = username;
		this.password = password;
		this.location = location;
		this.isManager = isManager;
		
	}
	
	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Employee.username = username;
	}

	public static void setPassword(String password) {
		Employee.password = password;
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		Employee.location = location;
	}

	public static Boolean getIsManager() {
		return isManager;
	}

	public static void setIsManager(Boolean isManager) {
		Employee.isManager = isManager;
	}
}
