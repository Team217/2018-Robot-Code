package frc.robot.commands;

import java.util.TimerTask;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that drives forwards/backwards in auton.
 * 
 * @author ThunderChickens 217
 */
public class autonCurvedDrive extends Command {
	double target = 0;
	double turnTarget;
	double speed = 0;
	double kP = 0;
	double kI = 0;
	double kD = 0;
	boolean forward = true;
	boolean timed = false;
	
	long delayTime = 0;
	java.util.Timer delay = new java.util.Timer();
	TimerTask delayTask = new TimerTask() {
		public void run() {
			Robot.kDriveBase.originalAngle = turnTarget;
		}
	};
	
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	
	/**
	 * A {@code Command} that drives the bot using an encoder target and {@code PID} while curving towards a target angle.
	 * </p>
	 * An {@code autonTurn} command may still be needed to complete the turn.
	 * 
	 * @param target1
	 *        The target encoder count
	 * @param turnTarget
	 *        The target angle to reach while driving
	 * @param kP1
	 *        The P value for {@code PID}
	 * @param kI1
	 *        The I value for {@code PID}
	 * @param kD1
	 *        The D value for {@code PID}
	 */
	public autonCurvedDrive(double target1, double turnTarget1, double kP1, double kI1, double kD1) {
        this(target1, turnTarget1, 0, kP1, kI1, kD1);
	}
	
	/**
	 * A {@code Command} that drives the bot using an encoder target and {@code PID} while curving towards a target angle. The curving is delayed.
	 * </p>
	 * An {@code autonTurn} command may still be needed to complete the turn.
	 * 
	 * @param target1
	 *        The target encoder count
	 * @param turnTarget
	 *        The target angle to reach while driving
	 * @param curveDelay
	 *        The delay before the bot starts curving
	 * @param kP1
	 *        The P value for {@code PID}
	 * @param kI1
	 *        The I value for {@code PID}
	 * @param kD1
	 *        The D value for {@code PID}
	 */
	public autonCurvedDrive(double target1, double turnTarget1, long curveDelay, double kP1, double kI1, double kD1) {
		super("autonCurvedDrive");
		requires(Robot.kDriveBase);
		target = target1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
		turnTarget = turnTarget1;
		forward = (target > 0.0);
		delayTime = curveDelay;
		timed = false;
	}
	
	/**
	 * A {@code Command} that drives the bot at a given speed for a given amount of time while curving towards a target angle.
	 * 
	 * @param speed1
	 *        The bot speed
	 * @param turnTarget
	 *        The target angle to reach while driving
	 * @param timeout
	 *        The time, in seconds, that the bot should drive
	 */
	public autonCurvedDrive(double speed1, double turnTarget1, double timeout) {
        this(speed1, turnTarget1, 0, timeout);
	}
	
	/**
	 * A {@code Command} that drives the bot at a given speed for a given amount of time while curving towards a target angle. The curving is delayed.
	 * 
	 * @param speed1
	 *        The bot speed
	 * @param turnTarget
	 *        The target angle to reach while driving
	 * @param curveDelay
	 *        The delay before the bot starts curving
	 * @param timeout
	 *        The time, in seconds, that the bot should drive
	 */
	public autonCurvedDrive(double speed1, double turnTarget1, long curveDelay, double timeout) {
		super("autonCurvedDrive");
		requires(Robot.kDriveBase);
		speed = speed1;
		turnTarget = turnTarget1;
		delayTime = curveDelay;
		timed = true;
		setTimeout(timeout);
	}
	
	/** Called just before this {@code Command} runs for the first time. */
	protected void initialize() {
		System.out.println("Doin a drive thing");
		RobotMap.rightMaster.resetEncoder();
		
		delay.schedule(delayTask, delayTime);
	}
	
	/** Called repeatedly when this {@code Command} is scheduled to run. */
	protected void execute() {
		if (timed) {
			Robot.kDriveBase.driveStraight(speed, 0);
		}
		if (forward) {
			Robot.kDriveBase.driveForward(target, kP, kI, kD, true);
		}
		else {
			Robot.kDriveBase.driveBackward(target, kP, kI, kD, true);
		}
	}
	
	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
	protected boolean isFinished() {
		return Robot.kDriveBase.drivePID_OnTarget;
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
