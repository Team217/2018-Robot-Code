package org.usfirst.frc.team217.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/**
 * WPI Talon SRX Class (Extended). Class supports communicating over CANbus and over ribbon-cable (CAN Talon SRX).
 * 
 * @author ThunderChickens 217, Cross the Road Electronics
 */
public class WPI_TalonSRX extends com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
{
	
	/**
	 * Constructor for creating a {@code TalonSRX} motor controller for FRC.
	 * 
	 * @param deviceNumber
	 *                  The Device ID of the motor controller
	 */
	public WPI_TalonSRX(int deviceNumber)
	{
		super(deviceNumber);
	}
	
	/**
	 * Resets the Quadrature Encoder.
	 * 
	 * @return error code
	 */
	public ErrorCode resetEncoder()
	{
		return getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	/** Returns the Quadrature Encoder position. */
	public int getEncoder()
	{
		return getSensorCollection().getQuadraturePosition();
	}
	
	/** Returns the Analog Encoder position. */
	public int getAnalogEnc()
	{
		return getSensorCollection().getAnalogIn();
	}
	
	/** Returns '1' iff forward limit switch is closed, 0 iff switch is open. This function works regardless if limit switch feature is enabled. */
	public boolean getLimitFwd()
	{
		return getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	/** Returns '1' iff reverse limit switch is closed, 0 iff switch is open. This function works regardless if limit switch feature is enabled. */
	public boolean getLimitRev()
	{
		return getSensorCollection().isRevLimitSwitchClosed();
	}
	
	/**
	 * Sets up the motor controller to have a current limit and use a Quadrature Encoder.
	 * 
	 * @param currentLimit
	 *                  The current limit of the motor controller
	 */
	public void setup(int currentLimit)
	{
		set(0);
		configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		resetEncoder();
//		enableCurrentLimit(true);
//		configContinuousCurrentLimit(currentLimit, 0);
	}
	
	/** Sets up the motor controller to have a current limit of 40 and use a Quadrature Encoder. */
	public void setup()
	{
		set(0);
		configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		resetEncoder();
//		enableCurrentLimit(true);
//		configContinuousCurrentLimit(40, 0);
	}

}
