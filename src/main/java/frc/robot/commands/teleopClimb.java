package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that deploys the climber in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopClimb extends Command {
	
	/** A {@code Command} that deploys the climber in teleop. */
    public teleopClimb() {
    	super("teleopClimb");
    }

    /** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	Robot.kClimber.deployClimber(Robot.m_oi.squareDriver.get());
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
