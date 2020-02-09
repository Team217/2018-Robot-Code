package frc.robot.commands.auton;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that drives forwards/backwards in auton.
 * 
 * @author ThunderChickens 217
 */
public class AutonDrive extends Command {
	double target = 0;
	double speed = 0;
	double kP = 0;
	double kI = 0;
	double kD = 0;
	boolean forward = true;
	boolean timed = false;
	boolean turnCorrection = true;
	
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	
	/**
	 * A {@code Command} that drives the bot using an encoder target and {@code PID}.
	 * 
	 * @param target1
	 *        The target encoder count
	 * @param kP1
	 *        The P value for {@code PID}
	 * @param kI1
	 *        The I value for {@code PID}
	 * @param kD1
	 *        The D value for {@code PID}
	 * @param gyroCorrectionEnabled
	 *        {@code true} if drive straight angle correction should be enabled
	 */
	public AutonDrive(double target1, double kP1, double kI1, double kD1, boolean gyroCorrectionEnabled) {
		super("autonDrive");
		requires(Robot.kDriveBase);
		target = target1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
		turnCorrection = gyroCorrectionEnabled;
		forward = (target > 0.0);
	}
	
	/**
	 * A {@code Command} that drives the bot at a given speed for a given amount of time.
	 * 
	 * @param speed1
	 *        The bot speed
	 * @param timeout
	 *        The time, in seconds, that the bot should drive
	 */
	public AutonDrive(double speed1, boolean gyroCorrectionEnabled, double timeout) {
		super("autonDrive");
		requires(Robot.kDriveBase);
		speed = speed1;
		timed = true;
		turnCorrection = gyroCorrectionEnabled;
		setTimeout(timeout);
	}
	
	/** Called just before this {@code Command} runs for the first time. */
	protected void initialize() {
		System.out.println("Doin a drive thing");
		RobotMap.rightMaster.resetEncoder();
	}
	
	/** Called repeatedly when this {@code Command} is scheduled to run. */
	protected void execute() {
		
		if (timed) {
			if (turnCorrection) {
				Robot.kDriveBase.driveStraight(speed, 0.0);
			}
			else {
				Robot.kDriveBase.drive(speed, 0.0, false);
			}
		}
		else if (forward) {
			Robot.kDriveBase.driveForward(target, kP, kI, kD, turnCorrection);
		}
		else {
			Robot.kDriveBase.driveBackward(target, kP, kI, kD, turnCorrection);
		}
	}
	
	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
	protected boolean isFinished() {
		return timed ? isTimedOut() : Robot.kDriveBase.drivePID_OnTarget;
	}
	
	/** Called once after {@code isFinished()} returns {@code true}. */
	protected void end() {
		RobotMap.rightMaster.set(0);
		RobotMap.leftMaster.set(0);
		RobotMap.rightMaster.resetEncoder();
	}
	
	/** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
	protected void interrupted() {
		RobotMap.rightMaster.set(0);
		RobotMap.leftMaster.set(0);
		RobotMap.rightMaster.resetEncoder();
	}
}
