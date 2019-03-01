package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that delays the auton.
 * 
 * @author ThunderChickens 217
 */
public class autonDelay extends Command {
	
	/**
	 * Delays auton execution for a given amount of time.
	 * 
	 * @param timeout
	 *              The time to delay the auton, in seconds
	 */
    public autonDelay(double timeout) {
        super("autonDelay");
        setTimeout(timeout);
    }

	/** Called just before this {@code Command} runs for the first time. */
    protected void initialize() {
    	
    }

	/** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	
    }

	/** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
    protected boolean isFinished() {
        return isTimedOut();
    }

	/** Called once after {@code isFinished()} returns {@code true}.  */
    protected void end() {
    	
    }

	/** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
    protected void interrupted() {
    	
    }
}

