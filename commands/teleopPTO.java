package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that controls the PTO in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopPTO extends Command {
	
	/** A {@code Command} that controls the PTO in teleop. */
    public teleopPTO() {
    	super("teleopPTO");
    }

	/** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	Robot.kClimber.driveMode();
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	if(Robot.m_oi.circleDriver.get()) {
    		Robot.kClimber.climbMode();
    	}
    	else if(Robot.m_oi.leftTriggerDriver.get()) {
    		Robot.kClimber.spoolOn();
    	}
    	else if(Robot.m_oi.triangleDriver.get()) {
    		Robot.kClimber.driveMode();
    	}
    	else if(Robot.m_oi.rightTriggerDriver.get()) {
    		Robot.kClimber.lockClimbMode();
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
