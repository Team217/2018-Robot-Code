package org.usfirst.frc.team217.robot;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class saves and reads field measurements to/from files on the roboRIO.
 * 
 * @author ThunderChickens 217
 */
public class Measurements
{	
	public static final String wallToSwitch = "alliance_wall_to_switch"; // Used
	public static final String switchToScale = "switch_to_scale"; // Used
	public static final String wallToScale = "alliance_wall_to_scale"; // Used
	public static final String leftWallToSwitch = "left_wall_to_switch";
	public static final String rightWallToSwitch = "right_wall_to_switch";
	public static final String leftWallToScale = "left_wall_to_scale";
	public static final String rightWallToScale = "right_wall_to_scale";
	public static final String switchToNullRight = "switch_to_null_right"; // Used
	public static final String switchToNullLeft = "switch_to_null_left"; // Used
	public static final String nullToScaleRight = "null_to_scale_right";
	public static final String nullToScaleLeft = "null_to_scale_left";
	public static final String switchToPlatform = "switch_to_platform"; // Used
	public static final String platformToNullZoneLeft = "platform_to_null_zone_left";
	public static final String platformToNullZoneRight = "platform_to_null_zone_right";
	public static final String scalePadDepth = "scale_pad_depth";
	public static final String scalePadWidth = "scale_pad_width"; // Used
	public static final String scaleWidth = "scale_width"; // Used
	public static final String switchDepth = "switch_depth"; // Used
	public static final String switchWidth = "switch_width"; // Used
	public static final String rightNullZoneDepth = "right_null_zone_depth"; // Used
	public static final String leftNullZoneDepth = "left_null_zone_depth"; // Used
	public static final String wallToNullZoneLeft = "alliance_wall_to_null_zone_left"; // Used
	public static final String wallToNullZoneRight = "alliance_wall_to_null_zone_right"; // Used
	public static final String exchangeRightToCenter = "exchange_right_to_center_line"; // Used
	public static final String centerLineToLeftSwitchEdge = "center_line_to_left_switch_edge"; // Used
	public static final String centerLineToRightSwitchEdge = "center_line_to_right_switch_edge"; // Used
	
	// Strings not used in the file but still useful
//	static final String scaleLeftOffset = "scale_left_offset";
//	static final String scaleRightOffset = "scale_right_offset";
//	static final String switchLeftOffset = "switch_left_offset";
//	static final String switchRightOffset = "switch_right_offset";

	static final String redTable = "red_side";
	static final String blueTable = "blue_side";

	private static File redFile = new File("/home/lvuser/red-measurements.json");
	private static File blueFile = new File("/home/lvuser/blue-measurements.json");
	
	/**
	 * Saves the default field measurements to two .json files as JSON code.
	 * 
	 * @throws IOException
	 */
	public static void saveToJSON() throws IOException
	{
		Map<String, String> obj = new LinkedHashMap<String, String>();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String formatted;
		
		if(redFile.createNewFile())
		{
			// Red Side
			obj.put(wallToSwitch, "140.0");
			obj.put(switchToScale, "103.65");
			obj.put(wallToScale, "299.65"); //ok
			obj.put(leftWallToSwitch, "85.25");
			obj.put(rightWallToSwitch, "85.25");
			obj.put(leftWallToScale, "71.57");
			obj.put(rightWallToScale, "71.57");
			obj.put(switchToNullRight, "92.0");
			obj.put(switchToNullLeft, "92.0");
			obj.put(nullToScaleRight, "11.65");
			obj.put(nullToScaleLeft, "11.65");
			obj.put(switchToPlatform, "65.47");
			obj.put(scalePadDepth, "48.7");
			obj.put(scalePadWidth, "36.7");
			obj.put(scaleWidth, "180.24");
			obj.put(switchDepth, "56.0");
			obj.put(switchWidth, "152.8");
			obj.put(rightNullZoneDepth, "72.0");
			obj.put(leftNullZoneDepth, "72.0");
			obj.put(wallToNullZoneLeft, "288.0"); //ok
			obj.put(wallToNullZoneRight, "288.0");
			obj.put(platformToNullZoneRight, "38.18");
			obj.put(platformToNullZoneLeft, "38.18");
			obj.put(exchangeRightToCenter, "12.0");
			obj.put(centerLineToLeftSwitchEdge, "76.4");
			obj.put(centerLineToRightSwitchEdge, "76.4");
			
			formatted = gson.toJson(obj);
			
			@SuppressWarnings("resource")
			FileWriter redToFile = new FileWriter(redFile);
			redToFile.write(formatted);
			redToFile.flush();
		}
		
		obj.clear();
		
		if(blueFile.createNewFile())
		{
			// Blue Side
			obj.put(wallToSwitch, "140.0");
			obj.put(switchToScale, "103.65");
			obj.put(wallToScale, "299.65");
			obj.put(leftWallToSwitch, "85.25");
			obj.put(rightWallToSwitch, "85.25");
			obj.put(leftWallToScale, "71.57");
			obj.put(rightWallToScale, "71.57");
			obj.put(switchToNullRight, "92.0");
			obj.put(switchToNullLeft, "92.0");
			obj.put(nullToScaleRight, "11.65");
			obj.put(nullToScaleLeft, "11.65");
			obj.put(switchToPlatform, "65.47");
			obj.put(scalePadDepth, "48.7");
			obj.put(scalePadWidth, "36.7");
			obj.put(scaleWidth, "180.24");
			obj.put(switchDepth, "56.0");
			obj.put(switchWidth, "152.8");
			obj.put(rightNullZoneDepth, "72.0");
			obj.put(leftNullZoneDepth, "72.0");
			obj.put(wallToNullZoneLeft, "288.0");
			obj.put(wallToNullZoneRight, "288.0");
			obj.put(platformToNullZoneRight, "38.18");
			obj.put(platformToNullZoneLeft, "38.18");
			obj.put(exchangeRightToCenter, "12.0");
			obj.put(centerLineToLeftSwitchEdge, "76.4");
			obj.put(centerLineToRightSwitchEdge, "76.4");
			
			formatted = gson.toJson(obj);
			
			@SuppressWarnings("resource")
			FileWriter blueToFile = new FileWriter(blueFile);
			blueToFile.write(formatted);
			blueToFile.flush();
		}
	}
	
	/**
	 * Returns a {@code HashMap} of the field measurements.
	 * 
	 * <p>{@code String} is the side name, and {@code HashMap<String, Double>} is a {@code HashMap}
	 * where {@code String} is the measurement name and {@code Double} is the measurement.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static HashMap<String, HashMap<String, Double>> getMeasurements() throws IOException, ParseException
	{
		saveToJSON();
		
		JSONParser parseFile = new JSONParser();
		
		Object redObject = parseFile.parse(new FileReader(redFile));
		JSONObject redObj = (JSONObject) redObject;

		Object blueObject = parseFile.parse(new FileReader(blueFile));
		JSONObject blueObj = (JSONObject) blueObject;
		
		while(redObj.size() != 26)
		{
			if(redFile.delete()) {
				saveToJSON();
				break;
			}
		}
		
		while(blueObj.size() != 26)
		{
			if(blueFile.delete()) {
				saveToJSON();
				break;
			}
		}
		
		HashMap<String, HashMap<String, Double>> measurements = new HashMap<String, HashMap<String, Double>>();
		
		HashMap<String, Double> redMeasurements = new HashMap<String, Double>();
		
		redMeasurements.put(wallToSwitch, Double.parseDouble(redObj.get(wallToSwitch).toString()));
		redMeasurements.put(switchToScale, Double.parseDouble(redObj.get(switchToScale).toString()));
		redMeasurements.put(wallToScale, Double.parseDouble(redObj.get(wallToScale).toString()));
		redMeasurements.put(leftWallToSwitch, Double.parseDouble(redObj.get(leftWallToSwitch).toString()));
		redMeasurements.put(rightWallToSwitch, Double.parseDouble(redObj.get(rightWallToSwitch).toString()));
		redMeasurements.put(leftWallToScale, Double.parseDouble(redObj.get(leftWallToScale).toString()));
		redMeasurements.put(rightWallToScale, Double.parseDouble(redObj.get(rightWallToScale).toString()));
		redMeasurements.put(switchToNullRight, Double.parseDouble(redObj.get(switchToNullRight).toString()));
		redMeasurements.put(switchToNullLeft, Double.parseDouble(redObj.get(switchToNullLeft).toString()));
		redMeasurements.put(nullToScaleRight, Double.parseDouble(redObj.get(nullToScaleRight).toString()));
		redMeasurements.put(nullToScaleLeft, Double.parseDouble(redObj.get(nullToScaleLeft).toString()));
		redMeasurements.put(switchToPlatform, Double.parseDouble(redObj.get(switchToPlatform).toString()));
		redMeasurements.put(platformToNullZoneLeft, Double.parseDouble(redObj.get(platformToNullZoneLeft).toString()));
		redMeasurements.put(platformToNullZoneLeft, Double.parseDouble(redObj.get(platformToNullZoneRight).toString()));
		redMeasurements.put(scalePadDepth, Double.parseDouble(redObj.get(scalePadDepth).toString()));
		redMeasurements.put(scalePadWidth, Double.parseDouble(redObj.get(scalePadWidth).toString()));
		redMeasurements.put(scaleWidth, Double.parseDouble(redObj.get(scaleWidth).toString()));
		redMeasurements.put(switchDepth, Double.parseDouble(redObj.get(switchDepth).toString()));
		redMeasurements.put(switchWidth, Double.parseDouble(redObj.get(switchWidth).toString()));
		redMeasurements.put(rightNullZoneDepth, Double.parseDouble(redObj.get(rightNullZoneDepth).toString()));
		redMeasurements.put(leftNullZoneDepth, Double.parseDouble(redObj.get(leftNullZoneDepth).toString()));
		redMeasurements.put(wallToNullZoneLeft, Double.parseDouble(redObj.get(wallToNullZoneLeft).toString()));
		redMeasurements.put(wallToNullZoneRight, Double.parseDouble(redObj.get(wallToNullZoneRight).toString()));
		redMeasurements.put(exchangeRightToCenter, Double.parseDouble(redObj.get(exchangeRightToCenter).toString()));
		redMeasurements.put(centerLineToLeftSwitchEdge, Double.parseDouble(redObj.get(centerLineToLeftSwitchEdge).toString()));
		redMeasurements.put(centerLineToRightSwitchEdge, Double.parseDouble(redObj.get(centerLineToRightSwitchEdge).toString()));
		measurements.put(redTable, redMeasurements);
		
		HashMap<String, Double> blueMeasurements = new HashMap<String, Double>();
		
		blueMeasurements.put(wallToSwitch, Double.parseDouble(blueObj.get(wallToSwitch).toString()));
		blueMeasurements.put(switchToScale, Double.parseDouble(blueObj.get(switchToScale).toString()));
		blueMeasurements.put(wallToScale, Double.parseDouble(blueObj.get(wallToScale).toString()));
		blueMeasurements.put(leftWallToSwitch, Double.parseDouble(blueObj.get(leftWallToSwitch).toString()));
		blueMeasurements.put(rightWallToSwitch, Double.parseDouble(blueObj.get(rightWallToSwitch).toString()));
		blueMeasurements.put(leftWallToScale, Double.parseDouble(blueObj.get(leftWallToScale).toString()));
		blueMeasurements.put(rightWallToScale, Double.parseDouble(blueObj.get(rightWallToScale).toString()));
		blueMeasurements.put(switchToNullRight, Double.parseDouble(blueObj.get(switchToNullRight).toString()));
		blueMeasurements.put(switchToNullLeft, Double.parseDouble(blueObj.get(switchToNullLeft).toString()));
		blueMeasurements.put(nullToScaleRight, Double.parseDouble(blueObj.get(nullToScaleRight).toString()));
		blueMeasurements.put(nullToScaleLeft, Double.parseDouble(blueObj.get(nullToScaleLeft).toString()));
		blueMeasurements.put(switchToPlatform, Double.parseDouble(blueObj.get(switchToPlatform).toString()));
		blueMeasurements.put(platformToNullZoneLeft, Double.parseDouble(blueObj.get(platformToNullZoneLeft).toString()));
		blueMeasurements.put(platformToNullZoneLeft, Double.parseDouble(blueObj.get(platformToNullZoneRight).toString()));
		blueMeasurements.put(scalePadDepth, Double.parseDouble(blueObj.get(scalePadDepth).toString()));
		blueMeasurements.put(scalePadWidth, Double.parseDouble(blueObj.get(scalePadWidth).toString()));
		blueMeasurements.put(scaleWidth, Double.parseDouble(blueObj.get(scaleWidth).toString()));
		blueMeasurements.put(switchDepth, Double.parseDouble(blueObj.get(switchDepth).toString()));
		blueMeasurements.put(switchWidth, Double.parseDouble(blueObj.get(switchWidth).toString()));
		blueMeasurements.put(rightNullZoneDepth, Double.parseDouble(blueObj.get(rightNullZoneDepth).toString()));
		blueMeasurements.put(leftNullZoneDepth, Double.parseDouble(blueObj.get(leftNullZoneDepth).toString()));
		blueMeasurements.put(wallToNullZoneLeft, Double.parseDouble(blueObj.get(wallToNullZoneLeft).toString()));
		blueMeasurements.put(wallToNullZoneRight, Double.parseDouble(blueObj.get(wallToNullZoneRight).toString()));
		blueMeasurements.put(exchangeRightToCenter, Double.parseDouble(blueObj.get(exchangeRightToCenter).toString()));
		blueMeasurements.put(centerLineToLeftSwitchEdge, Double.parseDouble(blueObj.get(centerLineToLeftSwitchEdge).toString()));
		blueMeasurements.put(centerLineToRightSwitchEdge, Double.parseDouble(blueObj.get(centerLineToRightSwitchEdge).toString()));
		measurements.put(blueTable, blueMeasurements);
		
		return measurements;
	}
}
