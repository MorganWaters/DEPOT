package eDepotSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Schedule {
	
	static void viewSchedule(String username) {
		BufferedReader in = new BufferedReader(new FileReader("<work-schedule.txt>"));
		String line;
                 while((line = in.readLine()) != null)
                {
                 System.out.println(line);
                }
                 in.close();
        	}

        public static void main(String[checkWorkSchedule] args) {
        Writer writer = null;
        try {
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog);

            // This will output the full path where the file will be written to...
	    File file = new File ("WorkSchedule/work-schedule.txt")
		    String path = file.getPath();
            System.out.println(logFile.getCanonicalPath());

            writer = new Writer(new FileWriter(logFile));
            writer.write("Booked Time Slot");
		
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
		
	}
	
	static void editSchedule() {
		
	}

}
