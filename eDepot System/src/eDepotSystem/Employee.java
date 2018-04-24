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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Employee.username = username;
	}

	public void setPassword(String password) {
		Employee.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		Employee.location = location;
	}

	public Boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		Employee.isManager = isManager;
	}
}
