package org.usfirst.frc.team217.robot.commands;

import java.util.TimerTask;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that runs the elevator in auton.
 * 
 * @author ThunderChickens 217
 */
public class autonSecondStageElevator extends Command {
	double target = 0;
	double position = 0;
	double kP = 0.0217;
	double kI = 0;
	double kD = 0;
	boolean readOnly = false;
	boolean isUp = true;
	double multiplier = 1;
	
	boolean delayFinished = true;
	long delayTime = 0;
	java.util.Timer delay = new java.util.Timer();
	TimerTask delayTask = new TimerTask() {
		public void run() {
			delayFinished = true;
		}
	};
	
	/**
	 * A {@code Command} that runs the elevator using an encoder target and {@code PID}.
	 * 
	 * @param target1
	 *          The target encoder count
	 * @param kP1
	 *          The P value for {@code PID}
	 * @param kI1
	 *          The I value for {@code PID}
	 * @param kD1
	 *          The D value for {@code PID}
	 */
	public autonSecondStageElevator(double target1, double kP1, double kI1, double kD1) {
		this(target1, kP1, kI1, kD1, 1.0);
	}
	
	public autonSecondStageElevator(double target1, double kP1, double kI1, double kD1, double mult) {
		super("autonSecondStageElevator");
		requires(Robot.kElevator);
		target = target1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
		readOnly = false;
		delayFinished = true;
		multiplier = mult;
	}
	
	/**
	 * A {@code Command} that runs the elevator using an encoder target and {@code PID} after a delay.
	 * 
	 * @param target1
	 *          The target encoder count
	 * @param delay1
	 *          The time, in milliseconds, to delay
	 * @param kP1
	 *          The P value for {@code PID}
	 * @param kI1
	 *          The I value for {@code PID}
	 * @param kD1
	 *          The D value for {@code PID}
	 */
	public autonSecondStageElevator(double target1, long delay1, double kP1, double kI1, double kD1) {
		this(target1, kP1, kI1, kD1);
		delayTime = delay1;
	}
	
	/** A {@code Command} that reads the elevator's encoder and checks if it reached the previously given target. */
	public autonSecondStageElevator() {
		super("autonSecondStageElevator");
		readOnly = true;
		delayFinished = true;
	}

	/** Called just before this {@code Command} runs the first time. */
	protected void initialize() {
    	System.out.println("Doin a lifty thing");
    	
    	isUp = (RobotMap.rightElevatorLift.getEncoder() > target); // Higher on the elevator is more negative
    	
    	if(!delayFinished) {
    		delay.schedule(delayTask, delayTime);
    	}
	}

	/** Called repeatedly when this {@code Command} is scheduled to run. */ 
	protected void execute() {
		if(!readOnly && delayFinished) {
			Robot.kElevator.autonSecondElevator(target, isUp, kP, kI, kD);
		}
	}
	

	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
	protected boolean isFinished() {
		if(readOnly) {
			return Robot.kElevator.elevatorPID_OnTarget;
		}
		return false;
	}

	/** Called once after {@code isFinished()} returns {@code true}. */
	protected void end() {
		if(!readOnly) {
			RobotMap.rightElevatorLift.set(0);
			RobotMap.leftElevatorLift.set(0);
		}
	}
	
	/** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
	protected void interrupted() {
		if(!readOnly) {
			RobotMap.rightElevatorLift.set(0);
			RobotMap.leftElevatorLift.set(0);
		}
	}
}
