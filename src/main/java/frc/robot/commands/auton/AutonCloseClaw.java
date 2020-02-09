package frc.robot.commands.auton;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that closes the claw in auton.
 * 
 * @author ThunderChickens 217
 */
public class AutonCloseClaw extends Command {
	/** A {@code Command} that closes the claw. */
    public AutonCloseClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("autonCloseClaw");
    	setTimeout(0.5);
    }

    /** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	
    }

    /** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	Robot.kClaw.closeClaw();
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
