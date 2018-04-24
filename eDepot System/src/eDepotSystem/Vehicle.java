package eDepotSystem;

public class Vehicle {
	private static String make;
	private static String model;
	private static String regNo;
	private static String weight;
	private static String location;
	private static String type;
	
	public Vehicle (String make, String model, String regNo, String weight, String location, String type) {
		this.make = make;
		this.model = model;
		this.regNo = regNo;
		this.weight = weight;
		this.location = location;
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
