package org.usfirst.frc.team217.robot;

import java.time.Clock;

/**
 * A class that runs and controls PID systems.
 * 
 * @author ThunderChickens 217
 */
public class PID
{
	// Made private because of getValue() functions
	private double kP = 0;
	private double kI = 0;
	private double kD = 0;
	private double target = 0;
	private double position = 0;
	
	// Always private
	private double currentError = 0;
	private double lastError = 0;
	private double aI = 0;
	private double P_Output, I_Output, D_Output;
	private int delay = 0;
	
	private static Clock clock = Clock.systemUTC();
	private long currentTime = clock.millis();
	
	/**
	 * Constructor to make a variable that holds information about and calculates information for a PID system.
	 * 
	 *  @param newP
	 *           The new kP value
	 *  @param newI
	 *           The new kI value
	 *  @param newD
	 *           The new kD value
	 *  @param timeout
	 *           The time to wait before updating I or D, in milliseconds
	 *  @exception IllegalArgumentException
	 *           if the newP, newI, or newD value is negative
	 */
	public PID(double newP, double newI, double newD, int timeout)
	{
		setP(newP);
		setI(newI);
		setD(newD);
		delay = timeout;
	}
	
	/**
	 * Constructor to make a variable that holds information about and calculates information for a PI system.
	 * 
	 * @param newP
	 *           The new kP value
	 * @param newI
	 *           The new kI value
	 * @param timeout
	 *           The time to wait before updating I, in milliseconds
	 * @exception IllegalArgumentException
	 *           if the newP, newI, or newD value is negative
	 */
	public PID(double newP, double newI, int timeout)
	{
		setP(newP);
		setI(newI);
		delay = timeout;
	}
	
	/** 
	 * Sets the kP value.
	 * 
	 * @param newP
	 *          The new kP value
	 * @exception IllegalArgumentException
	 *          if newP is negative
	 */
	private void setP(double newP)
	{
		if(newP < 0)
		{
			throw new IllegalArgumentException("Illegal kP Value: " + newP + "\nValue cannot be negative");
		}
		else
		{
			kP = newP;
		}
	}
	
	/** 
	 * Sets the kI value.
	 * 
	 * @param newI
	 *          The new kI value
	 * @exception IllegalArgumentException
	 *          if newI is negative
	 */
	private void setI(double newI)
	{
		if(newI < 0)
		{
			throw new IllegalArgumentException("Illegal kI Value: " + newI + "\nValue cannot be negative");
		}
		else
		{
			kI = newI;
		}
	}
	
	/** 
	 * Sets the kD value.
	 * 
	 * @param newD
	 *          The new kD value
	 * @exception IllegalArgumentException
	 *          if newD is negative
	 */
	private void setD(double newD)
	{
		if(newD < 0)
		{
			throw new IllegalArgumentException("Illegal kD Value: " + newD + "\nValue cannot be negative");
		}
		else
		{
			kD = newD;
		}
	}
	
	/**
	 * Sets the given values as the values used for the PID.
	 * 
	 * @param newP
	 *          The new kP value
	 * @param newI
	 *          The new kI value
	 * @param newD
	 *          The new kD value
	 * @exception IllegalArgumentException
	 *          if newP, newI, or newD is negative
	 */
	public void setPID(double newP, double newI, double newD)
	{
		setP(newP);
		setI(newI);
		setD(newD);
		resetError();
	}
	
	/**
	 * Sets the given values as the values used for the PI.
	 * 
	 * @param newP
	 *          The new kP value
	 * @param newI
	 *          The new kI value
	 * @exception IllegalArgumentException
	 *          if newP or newI is negative
	 */
	public void setPID(double newP, double newI)
	{
		setP(newP);
		setI(newI);
		setD(0);
		resetError();
	}
	
	/**
	 * Sets the timeout.
	 * 
	 * @param timeout
	 *             The time to wait before updating I or D, in milliseconds
	 */
	public void setTimeout(int timeout)
	{
		delay = timeout;
	}
	
	/** Calculates the current error. */
	private void calcError()
	{
		currentError = target - position;
	}
	
	/** Calculates the Proportional output. */
	private void calcP_Output()
	{
		P_Output = kP * currentError;
	}
	
	/** Calculates the Integral output. */
	private void calcI_Output()
	{
		I_Output = (kI * currentError) + aI;
		calcAccumulatedI();
	}
	
	/** Calculates the Accumulated Integral output for use by the I Output calculation. */
	private void calcAccumulatedI()
	{
		if(clock.millis() >= currentTime + delay) // Wait for [delay] milliseconds because we don't get new encoder values until then
		{
			calcError();
			aI += kI * currentError;
			currentTime = clock.millis();
		}
	}
	
	/** Calculates the Derivative output. */
	private void calcD_Output()
	{
		D_Output = kD * (currentError - lastError);
		updateError();
	}
	
	/** Returns the P output value. */
	private double getP_Output()
	{
		calcP_Output();
		return P_Output;
	}
	
	/** Returns the I output value. */
	private double getI_Output()
	{
		calcI_Output();
		return I_Output;
	}
	
	/** Returns the D output value. */
	private double getD_Output()
	{
		calcD_Output();
		return D_Output;
	}
	
	/** Returns the current target. */
	public double getTarget()
	{
		return target;
	}
	
	/** Returns the current position. */
	public double getPosition()
	{
		return position;
	}
	
	/** Returns the current kP value. */
	public double getP()
	{
		return kP;
	}
	
	/** Returns the current kI value. */
	public double getI()
	{
		return kI;
	}
	
	/** Returns the current kD value. */
	public double getD()
	{
		return kD;
	}
	
	/** Returns the current error. */
	public double getCurrentError()
	{
		calcError();
		return currentError;
	}
	
	/** Returns the previous error. */
	public double getLastError()
	{
		updateError();
		return lastError;
	}
	
	/** Returns the Accumulated I Output value. */
	public double getAccumulatedI()
	{
		calcAccumulatedI();
		return aI;
	}
	
	/**
	 * Returns the motor output value.
	 * 
	 * @param pos
	 *         The current position
	 * @param tar
	 *         The desired target
	 */
	public double getOutput(double pos, double tar)
	{
		target = tar;
		position = pos;
		calcError();
		double output = getP_Output() + getI_Output() + getD_Output();
		return output;
	}
	
	/** Updates the error and last error values. */
	private void updateError()
	{
		if(clock.millis() >= currentTime + delay) // Wait for [delay] milliseconds before updating the last error
		{
			calcError();
			lastError = currentError;
		}
	}
	
	/** Resets kP, kI, and kD to 0. */
	public void resetPID()
	{
		setPID(0.0, 0.0, 0.0);
		resetError();
	}
	
	/** Resets the accumulated I value and the last error value. */
	public void resetError()
	{
		aI = 0;
		lastError = 0;
	}
	
}