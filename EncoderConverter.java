package org.usfirst.frc.team217.robot;

/**
 * This class converts between inches and encoder ticks.
 * 
 * @author ThunderChickens 217
 */
public class EncoderConverter
{
	
	/**
	 * Converts inches to encoder ticks.
	 * 
	 * @param inches
	 *            Inches to convert
	 * @param diameter
	 *            Size of diameter in inches
	 * @param ticksPerRev
	 *            Number of encoder ticks per one revolution
	 */
	public static double inchToEnc(double inches, double diameter, double ticksPerRev)
	{
		double encTicks = inches / (Math.PI * diameter) * ticksPerRev;
		
		return encTicks;
	}
	
	/**
	 * Converts encoder ticks to inches.
	 * 
	 * @param encTicks
	 *            Encoder Ticks to convert
	 * @param diameter
	 *            Size of diameter in inches
	 * @param ticksPerRev
	 *            Number of encoder ticks per one revolution
	 */
	public static double encToInch(double encTicks, double diameter, double ticksPerRev)
	{
		double inches = encTicks / ticksPerRev * Math.PI * diameter;
		
		return inches;
	}
}
