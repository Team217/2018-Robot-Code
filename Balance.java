package org.usfirst.frc.team217.robot;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * A class that saves each character of the FMS code to an ArrayList and determines which sides are your platforms.
 * 
 * @author ThunderChickens 217
 */
public class Balance {
	enum Type {
		aSwitch,
		theScale,
	}
	
	int characterIndex;
	private boolean isLeftPad;
	private String name;
	Type balanceType;
	
	/**
	 * A constructor that holds which side of a switch/scale (balance) of the designated index is your alliance's side.
	 * 
	 * <p>
	 * Index 0 is your switch, 1 is the scale, 2 is their switch.
	 * 
	 * @param ourSide
	 *        The character from the FMS that represents which side is yours
	 * @param index
	 *        The index of the FMS data that you want to use
	 */
	
	// true if left pad is our, false if not.
	public Balance(int index) {
		// character is from string sent to us by FMS, index is the index of the character in that string
		// set isLeftPad for switches and scale
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		if (Character.toUpperCase(gameData.charAt(index)) == 'L') {
			isLeftPad = true;
		}
		else {
			isLeftPad = false;
		}
		
		if (index == 0) {
			balanceType = Type.aSwitch;
			name = "Our Switch";
		}
		else if (index == 1) {
			balanceType = Type.theScale;
			name = "Scale";
		}
		else {
			balanceType = Type.aSwitch;
			name = "Their Switch";
		}
		
	}
	
	/** Returns {@code true} if the left pad is yours. */
	public boolean getIsLeftPad() {
		return isLeftPad;
	}
	
	/** Returns the name of the balance. */
	public String getName() {
		return name;
	}
	
}
