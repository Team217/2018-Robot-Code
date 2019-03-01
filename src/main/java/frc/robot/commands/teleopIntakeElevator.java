package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that controls the intake lift in teleop.
 * 
 * @author ThunderChickens 217
 * @deprecated
 *          We do not have an intake elevator anymore
 */
public class teleopIntakeElevator extends Command {
	
	/** @deprecated We do not have an intake elevator anymore */
    public teleopIntakeElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("teleopIntakeElevator");
    	requires(Robot.kElevator);
    }

    /** @deprecated We do not have an intake elevator anymore */
    protected void initialize() {
    }

    /** @deprecated We do not have an intake elevator anymore */
    protected void execute() {
    	//Robot.kElevators.intakeElevator();
    }
    
    /** @deprecated We do not have an intake elevator anymore */
    protected boolean isFinished() {
        return false;
    }

    /** @deprecated We do not have an intake elevator anymore */
    protected void end() {
    }

    /** @deprecated We do not have an intake elevator anymore */
    protected void interrupted() {
    }
}
