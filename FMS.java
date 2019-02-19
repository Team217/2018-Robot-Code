package org.usfirst.frc.team217.robot;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class is used to log FMS data and check for field faults related to the FMS code.
 * 
 * @author ThunderChickens 217
 */
public class FMS {
	// NOTE: Before going to queue, use the driver station to run the auton (you can end it after autonomousInit()) with
	//       the code "LLR" because this is an impossible code.
	//       The Driver Station will default to that if the FMS screws up, so isFieldFault() will return true, and you
	//       should use that boolean to automatically select a drive straight auton.
	
	// Valid FMS codes
	static final String[] combs = {"LLL", "RRR", "LRL", "RLR"};
	
	// Easier to do a .contains(String) to an ArrayList than a for(String : String[])
	static final ArrayList<String> possibleCombinations = new ArrayList<String>(Arrays.asList(combs));
	
	/**
	 * Checks the FMS code for a potential Arcade Fault and, if one is detected, spends up to 5 seconds trying to find a correct FMS code.
	 * 
	 * @return
	 *                 {@code true} if no correct FMS code could be retrieved within the 5 seconds and if there is no Arcade Fault,
	 *                 {@code false} if there is no Arcade Fault
	 * @throws IOException
	 *                 If an I/O error occured when writing FMS log data
	 */
	public static boolean isArcadeFault() throws IOException {
		long startTime = Clock.systemUTC().millis();
		boolean fieldFault = true;
		
		// Log file saved on roboRIO here
		String dateTime = "/home/lvuser/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM.dd.yy_HH-mm-ss"))
				+ ".log";
		
		long lastTime = startTime - 1000;
		
		// Do this for up to 5 seconds as long as it could be an Arcade Fault
		while(fieldFault && Clock.systemUTC().millis() <= startTime + 5000) {
			if(Clock.systemUTC().millis() >= lastTime + 100) { // Perform this every 100 milliseconds so the field doesn't break
				String gameData = DriverStation.getInstance().getGameSpecificMessage();
				
				lastTime = Clock.systemUTC().millis();
				log(dateTime, gameData);
				
				if(possibleCombinations.contains(gameData)) { // If it's a correct code, you're good
					fieldFault = false;
					break; // No field fault, don't need to continue to loop
				}
				fieldFault = true;
			}
		}
		
		File logFile = new File(dateTime);
		logFile.createNewFile(); // Creates the file if it does not yet exist
		
		FileWriter fileOut = new FileWriter(logFile, true); // True says we want to add to the end of the file, not the start
		
		if(fieldFault) {
			// Writes error to log
			fileOut.write("Game Over! Arcade Fault Detected. Please contact the FTA after the match.");
		}
		else {
			// Writes a message to log saying that there are no known errors, but there could still be some
			fileOut.write("Victory! No Arcade Fault detected.");
		}
		fileOut.close();
		
		return fieldFault;
	}
	
	/**
	 * Logs the FMS data into the given file using the given data. Logs any warnings that may go with it, such as
	 * if an invalid FMS code was detected at the time of execution.
	 * 
	 * @param fileName
	 *              The name of the file, including file extension and full path
	 * @param data
	 *              The FMS data string
	 * @throws IOException
	 *              If an I/O error occurred when creating or writing to the log file
	 */
	public static void log(String fileName, String data) throws IOException {
		File logFile = new File(fileName);
		logFile.createNewFile(); // Creates the file if it does not yet exist
		
		FileWriter fileOut = new FileWriter(logFile, true); // True says we want to add to the end of the file, not the start
		
		// If an FMS code is already in the file, then there was a problem if this was called again, so log a potential error (warning)
		if(logFile.length() != 0) {
			fileOut.write("Warning: Invalid FMS Game Data Detected.");
			fileOut.write(System.lineSeparator());
			fileOut.write("Pinging the FMS for a new code...");
			fileOut.write(System.lineSeparator());
		}
		
		fileOut.write(data);
		fileOut.write(System.lineSeparator());
		fileOut.close();
	}
}