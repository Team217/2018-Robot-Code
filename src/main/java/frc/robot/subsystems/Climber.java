package frc.robot.subsystems;

import frc.robot.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Runs the bot's climb and PTO systems.
 * 
 * @author ThunderChickens 217
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	DoubleSolenoid driveBaseSolenoid = RobotMap.driveBase;
	Solenoid climberSpool = RobotMap.climberSpool;
	Solenoid climberUp = RobotMap.climberUp;
	
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
	
	/** Sets the bot's PTO to drive mode. */
	public void driveMode() {
		driveBaseSolenoid.set(Value.kForward);
		climberSpool.set(false);
		currentPTO = PTOMode.driveMode;
	}
	
	public void spoolOn() {
		driveBaseSolenoid.set(Value.kForward);
		climberSpool.set(true);
		currentPTO = PTOMode.driveMode;
	}
	
	/** Sets the bot's PTO to climb mode. */
	public void climbMode() {
		driveBaseSolenoid.set(Value.kReverse);
		climberSpool.set(true);
		currentPTO = PTOMode.climbMode;
	}
	
	public void lockClimbMode() {
		driveBaseSolenoid.set(Value.kReverse);
		climberSpool.set(false);
		currentPTO = PTOMode.climbMode;
	}
	
	/** Deploys the climber. */
	public void deployClimber(boolean deployClimb) {
		if (deployClimb)
		{
			driveBaseSolenoid.set(Value.kOff);
			climberUp.set(true);
		} 
		else
		{
			climberUp.set(false);
		}
	}
}
