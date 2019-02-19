package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that controls the intake in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopIntake extends Command {
	
	/** A {@code Command} that controls the intake in teleop. */
    public teleopIntake() {
    	super("teleopIntake");

    }

	/** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() { 
    	if(Robot.m_oi.rightTriggerOper.get()) { // If intake is running via the open claw command, don't do anything
    		return;
    	}
    	
    	if(Robot.m_oi.xOper.get()) {
    		Robot.kClaw.intake(.8);
    	}
    	else if(Robot.m_oi.rightBumperOper.get()) {
    		Robot.kClaw.intake(-.5);
    	}
    	else if(Robot.m_oi.oper.getPOV() == 0) {
    		Robot.kClaw.intake(-.75);
    	}
    	else if(Robot.m_oi.oper.getPOV() == 180) {
    		Robot.kClaw.intake(-.35);
    	}
    	else {
    		Robot.kClaw.intake(0.0);
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
