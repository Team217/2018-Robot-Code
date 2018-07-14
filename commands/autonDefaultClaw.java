package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that sets claw to default in auton.
 * 
 * @author ThunderChickens 217
 */
public class autonDefaultClaw extends Command {
	
	/** A {@code Command} that sets claw to default in auton. */
    public autonDefaultClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("autonDefaultClaw");
    	setTimeout(0.5);
    }

    /** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

    /** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	Robot.kClaw.defaultClaw();
    }

    /** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
    protected boolean isFinished() {
        return isTimedOut();
    }

    /** Called once after {@code isFinished()} returns {@code true}. */
    protected void end() {
    	
    }

    /** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
    protected void interrupted() {
    	
    }
}