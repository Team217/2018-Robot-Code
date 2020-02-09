package frc.robot.commands.teleop;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that controls the drivebase in teleop.
 * 
 * @author ThunderChickens 217
 */
public class TeleopDriveBase extends Command {
	boolean antiTipOn = true;
	
	/** A {@code Command} that controls the drivebase in teleop. */
    public TeleopDriveBase() {
    	super("teleopDriveBase");
    }

	/** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	double speed = -Robot.deadband(Robot.m_oi.driver.getY(), 0.1);
    	double turn = Robot.deadband(Robot.m_oi.driver.getZ(), 0.1);
    	
    	if(Robot.m_oi.leftBumperDriver.get()) {
    		antiTipOn = false;
    	}
    	else if(Robot.m_oi.rightBumperDriver.get()) {
    		antiTipOn = true;
    	}
    	
    	Robot.kDriveBase.drive(speed, turn, antiTipOn);
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
