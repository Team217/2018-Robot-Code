package frc.robot.subsystems;

import frc.robot.*;
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

	WPI_TalonSRX intakeFlip = RobotMap.intakeFlip;
	WPI_VictorSPX leftIntake = RobotMap.leftIntake;
	WPI_VictorSPX rightIntake = RobotMap.rightIntake;
	DoubleSolenoid shortArm = RobotMap.shortArm;
	DoubleSolenoid longArm = RobotMap.longArm;
	
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
		leftIntake.set(speed);
		rightIntake.set(speed);
	}
	
	/**
	 * Runs the intake flip (up and down).
	 * 
	 * @param speed
	 *           The speed of the intake flip
	 */
	public void armFlip(double speed) {
		intakeFlip.set(speed);
	}
	
	/** Sets the claw to the open state. */
	public void openClaw() {
		shortArm.set(DoubleSolenoid.Value.kForward);
		longArm.set(DoubleSolenoid.Value.kForward);
	}
	
	/** Sets the claw to the default cube pickup state. */
	public void defaultClaw() {
		shortArm.set(DoubleSolenoid.Value.kForward);
		longArm.set(DoubleSolenoid.Value.kReverse);
	}
	
	/** Sets the claw to the closed (narrow cube) state. */
	public void closeClaw() {
		shortArm.set(DoubleSolenoid.Value.kReverse);
		longArm.set(DoubleSolenoid.Value.kReverse);
	}

}
