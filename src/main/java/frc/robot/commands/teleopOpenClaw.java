package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that opens/closes the claw in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopOpenClaw extends Command {

	/** A {@code Command} that opens/closes the claw in teleop. */
    public teleopOpenClaw() {
    	super("teleopOpenClaw");
    }

	/** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	if(Robot.m_oi.circleOper.get()) {
    		Robot.kClaw.closeClaw();
    	}
    	else if(Robot.m_oi.triangleOper.get()) {
    		Robot.kClaw.defaultClaw();
    	}
    	else if(Robot.m_oi.rightTriggerOper.get()) {
    		Robot.kClaw.openClaw();
    		Robot.kClaw.intake(-.2);
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
