package org.usfirst.frc.team217.robot.subsystems;

import org.usfirst.frc.team217.robot.RobotMap;

import org.usfirst.frc.team217.robot.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Runs the bot's claw system.
 * 
 * @author ThunderChickens 217
 */
public class Claw extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	WPI_TalonSRX intakeFlip1 = RobotMap.intakeFlip;
	WPI_VictorSPX leftIntake1 = RobotMap.leftIntake;
	WPI_VictorSPX rightIntake1 = RobotMap.rightIntake;
	DoubleSolenoid shortArm1 = RobotMap.shortArm;
	DoubleSolenoid longArm1 = RobotMap.longArm;
	
	/** The default command to run during initialization. */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	/**
	 * Runs the intake wheels at a given speed.
	 * 
	 * @param speed
	 *           The intake speed
	 */
	public void intake(double speed) {
		leftIntake1.set(speed);
		rightIntake1.set(speed);
	}
	
	/**
	 * Runs the intake flip (up and down).
	 * 
	 * @param speed
	 *           The speed of the intake flip
	 */
	public void armFlip(double speed) {
		intakeFlip1.set(speed);
	}
	
	/** Sets the claw to the open state. */
	public void openClaw() {
		shortArm1.set(DoubleSolenoid.Value.kForward);
		longArm1.set(DoubleSolenoid.Value.kForward);
	}
	
	/** Sets the claw to the default cube pickup state. */
	public void defaultClaw() {
		shortArm1.set(DoubleSolenoid.Value.kForward);
		longArm1.set(DoubleSolenoid.Value.kReverse);
	}
	
	/** Sets the claw to the closed (narrow cube) state. */
	public void closeClaw() {
		shortArm1.set(DoubleSolenoid.Value.kReverse);
		longArm1.set(DoubleSolenoid.Value.kReverse);
	}

}
