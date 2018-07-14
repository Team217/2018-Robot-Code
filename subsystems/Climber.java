package org.usfirst.frc.team217.robot.subsystems;

import org.usfirst.frc.team217.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Runs the bot's climb and PTO systems.
 * 
 * @author ThunderChickens 217
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	DoubleSolenoid driveBaseSolenoid1 = RobotMap.driveBase;
	Solenoid climberSpool1 = RobotMap.climberSpool;
	Solenoid climberUp1 = RobotMap.climberUp;
	
	enum PTOMode {
		driveMode,
		climbMode
	};
	public static PTOMode currentPTO = PTOMode.driveMode;
	
	/** The default command to run during initialization. */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

//	public void PTO() {
//		if (Robot.m_oi.circleDriver.get())
//		{
//			driveBaseSolenoid1.set(Value.kReverse);
//			climberSpool1.set(true);
//		}
//		else if (Robot.m_oi.triangleDriver.get()) 
//		{
//			driveBaseSolenoid1.set(Value.kForward);
//			climberSpool1.set(false);
//		}
//		
//		if (Robot.m_oi.squareDriver.get())
//		{
//			driveBaseSolenoid1.set(Value.kOff);
//			climberUp1.set(true);
//		} 
//		else
//		{
//			climberUp1.set(false);
//		}
//	}
	
	/** Sets the bot's PTO to drive mode. */
	public void driveMode() {
		driveBaseSolenoid1.set(Value.kForward);
		climberSpool1.set(false);
		currentPTO = PTOMode.driveMode;
	}
	
	public void spoolOn() {
		driveBaseSolenoid1.set(Value.kForward);
		climberSpool1.set(true);
		currentPTO = PTOMode.driveMode;
	}
	
	/** Sets the bot's PTO to climb mode. */
	public void climbMode() {
		driveBaseSolenoid1.set(Value.kReverse);
		climberSpool1.set(true);
		currentPTO = PTOMode.climbMode;
	}
	
	public void lockClimbMode() {
		driveBaseSolenoid1.set(Value.kReverse);
		climberSpool1.set(false);
		currentPTO = PTOMode.climbMode;
	}
	
	/** Deploys the climber. */
	public void deployClimber(boolean deployClimb) {
		if (deployClimb)
		{
			driveBaseSolenoid1.set(Value.kOff);
			climberUp1.set(true);
		} 
		else
		{
			climberUp1.set(false);
		}
	}
}
