package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that controls the elevatoe in teleop.
 * 
 * @author ThunderChickens 217
 */
public class teleopSecondStageElevator extends Command {
	
	/** A {@code Command} that controls the elevatoe in teleop. */
    public teleopSecondStageElevator() {
    	super("teleopSecondStageElevator");
    }

	/** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	double leftAnalog = Robot.deadband(Robot.m_oi.oper.getY(), 0.1);
    	Robot.kElevator.secondStageElevator(leftAnalog);
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
