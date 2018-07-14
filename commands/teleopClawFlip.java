package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;
import org.usfirst.frc.team217.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that flips the claw up/down in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopClawFlip extends Command {
	
	/** A {@code Command} that flips the claw up/down in teleop. */
	public teleopClawFlip() {
		super("teleopClawFlip");
	}

	/** Called just before this {@code Command} runs the first time. */
	protected void initialize() {
		
	}

	/** Called repeatedly when this {@code Command} is scheduled to run. */
	protected void execute() {
		if (Robot.m_oi.leftBumperOper.get()) {
			if(RobotMap.rightElevatorLift.getEncoder() < -5000) {
				Robot.kClaw.armFlip(-.75);
			}
			else {
			Robot.kClaw.armFlip(-1);
			}
		} else if (Robot.m_oi.leftTriggerOper.get()) {
			if(RobotMap.rightElevatorLift.getEncoder() < -5000) {
				Robot.kClaw.armFlip(.45);
			}
			else {
			Robot.kClaw.armFlip(.75);
			}
		} else {
			Robot.kClaw.armFlip(0);
		}
	}

	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
	protected boolean isFinished() {
		return false;
	}

	/** Called once after {@code isFinished()} returns {@code true}. */
	protected void end() {
		
	}

	/** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
	protected void interrupted() {
		
	}
}
