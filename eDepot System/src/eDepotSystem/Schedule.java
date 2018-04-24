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
		    File file = new File ("WorkSchedule/work-schedule.txt");
			    String path = file.getPath();
	            System.out.println(logFile.getCanonicalPath());
	
	            writer = new Writer(new FileWriter(logFile));
	            writer.write(Employee.getUsername() + (" has viewed the work schedule"));
			
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
			   Throwable.getStackTrace();
	            }
	        }
        }
   
	

    private static void writeToFile() {
        try {
            BufferedWriter bufwriter = new BufferedWriter(new FileWriter("work-schedule.txt"));
            bufwriter.write(stringBufferOfData.toString());//writes the edited string buffer to the new file
            bufwriter.close();//closes the file

        } catch (Exception e) {//if an exception occurs
            System.out.println("Error occured while attempting to write to file: " + e.getMessage());
        }
    }

    private static void replacement() {
        System.out.println("Please enter the contents of a line you would like to edit: ");//prompt for a line in file to edit
        String lineToEdit = sc.nextLine();//read the line to edit

        System.out.println("Please enter the the replacement text: ");//prompt for a line in file to replace
        String replacementText = sc.nextLine();//read the line to replace

        //System.out.println(sb);//used for debugging to check that my stringbuffer has correct contents and spacing

        int startIndex = String.indexOf(lineToEdit);//now we get the starting point of the text we want to edit
        int endIndex = startIndex + lineToEdit.length();//now we add the staring index of the text with text length to get the end index

        String.replace(startIndex, endIndex, replacementText);//this is where the actual replacement of the text happens

        System.out.println("Here is the new edited text:\n" + stringBufferOfData); //used to debug and check the string was replaced


    }
}
		
	}

}
