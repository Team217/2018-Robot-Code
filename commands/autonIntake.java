package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that runs the intake wheels in auton.
 * 
 * @author ThunderChickens 217
 */
public class autonIntake extends Command {
	double intakeSpeed = 0.0;
	
	/**
	 * A {@code Command} that runs the intake wheels.
	 * 
	 * @param speed
	 *           The intake speed
	 * @param timeout
	 *           The time that the {@code Command} lasts, in seconds
	 */
	public autonIntake(double speed, double timeout) {
		super("autonIntake");
		requires(Robot.kClaw);
		
		intakeSpeed = speed;
		setTimeout(timeout);
	}
	
	/** Called just before this {@code Command} runs the first time. */
	protected void initialize() {

	}
	
	/** Called repeatedly when this Command is scheduled to run. */
	protected void execute() {
		Robot.kClaw.intake(intakeSpeed);
	}
	
	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
	protected boolean isFinished() {
		return isTimedOut();
	}

	/** Called once after {@code isFinished()} returns {@code true}. */
	protected void end() {
		Robot.kClaw.intake(0.0);
	}
	
	/** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
    protected void interrupted() {
		Robot.kClaw.intake(0.0);
    }
}
