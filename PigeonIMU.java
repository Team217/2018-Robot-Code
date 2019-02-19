package org.usfirst.frc.team217.robot;

import com.ctre.phoenix.ErrorCode;

/**
 * Pigeon IMU Class (Extended). Class supports communicating over CANbus and over ribbon-cable (CAN Talon SRX).
 * 
 * @author ThunderChickens 217, Cross the Road Electronics
 */
public class PigeonIMU extends com.ctre.phoenix.sensors.PigeonIMU {
	/**
	 * Constructor for creating a {@code PigeonIMU} object.
	 * 
	 * @param deviceNumber
	 *                  The Device ID of the {@code PigeonIMU}
	 */
	public PigeonIMU(int deviceNumber) {
		super(deviceNumber);
	}
	
	/** Returns the Yaw (horizontal) angle of the {@code PigeonIMU}. */
	public double getAngle() {
		double[] ypr = new double[3];
		getYawPitchRoll(ypr);
		return -ypr[0];
	}
	
	/** Returns the Pitch (front and back tip) angle of the {@code PigeonIMU}. */
	public double getPitch() {
		double[] ypr = new double[3];
		getYawPitchRoll(ypr);
		return -ypr[1];
	}
	
	/** Returns the Roll (left and right tip) angle of the {@code PigeonIMU}. */
	public double getRoll() {
		double[] ypr = new double[3];
		getYawPitchRoll(ypr);
		return ypr[2];
	}
	
	/**
	 * Resets the Yaw (horizontal) angle of the {@code PigeonIMU} to 0.
	 * 
	 * @return Error Code generated by function. 0 indicates no error
	 */
	public ErrorCode reset() {
		return setYaw(0, 0);
	}
	
}
