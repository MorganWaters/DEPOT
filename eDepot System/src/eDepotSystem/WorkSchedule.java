package eDepotSystem;

mport java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorkSchedule {
	
	private static String vehicleReg;
	private static String driver;
	private static String clientName;
	private static String location;
	private static String startDate;
	private static String startTime;
	private static String endDate;
	private static String endTime;
	private static String pending;
	
	public WorkSchedule(String vehicleReg, String driver, String clientName, String location, String startDate, String startTime, String endDate, String endTime, String pending ) {
		this.vehicleReg = vehicleReg;
		this.driver = driver;
		this.clientName = clientName;
		this.location = location;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.pending = pending;
	}

	public static String getVehicleReg() {
		return vehicleReg;
	}

	public static void setVehicleReg(String vehicleReg) {
		WorkSchedule.vehicleReg = vehicleReg;
	}

	public static String getDriver() {
		return driver;
	}

	public static void setDriver(String driver) {
		WorkSchedule.driver = driver;
	}

	public static String getClientName() {
		return clientName;
	}

	public static void setClientName(String clientName) {
		WorkSchedule.clientName = clientName;
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		WorkSchedule.location = location;
	}

	public static String getStartDate() {
		return startDate;
	}

	public static void setStartDate(String startDate) {
		WorkSchedule.startDate = startDate;
	}

	public static String getStartTime() {
		return startTime;
	}

	public static void setStartTime(String startTime) {
		WorkSchedule.startTime = startTime;
	}

	public static String getEndDate() {
		return endDate;
	}

	public static void setEndDate(String endDate) {
		WorkSchedule.endDate = endDate;
	}

	public static String getEndTime() {
		return endTime;
	}

	public static void setEndTime(String endTime) {
		WorkSchedule.endTime = endTime;
	}
        public static void viewSchedule(String username) {
		
        }

        public String readFile(String filename)
		
        {
        String viewSchedule = null;
        File file = new File("vehicle.txt");  
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            viewSchedule = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return viewSchedule;
    }
  }
}
