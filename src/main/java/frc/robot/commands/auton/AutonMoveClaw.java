package frc.robot.commands.auton;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that flips the claw up and down in auton.
 * 
 * @author ThunderChickens 217
 */
public class AutonMoveClaw extends Command {
	double flipSpeed = 0.5;
	
	/**
	 * A {@code Command} that flips the claw down.
	 * 
	 * @param timeout
	 *             The time, in seconds, the claw should spend flipping down
	 */
	public AutonMoveClaw(double timeout) {
		this(0.5, timeout);
    }
	
	/**
	 * A {@code Command} that flips the claw up/down.
	 * 
	 * @param speed
	 *             The speed of the claw flip
	 * @param timeout
	 *             The time, in seconds, the claw should spend flipping up/down
	 */
    public AutonMoveClaw(double speed, double timeout) {
		super("autonMoveClaw");
        requires(Robot.kClaw);
    	
    	flipSpeed = speed;
    	setTimeout(timeout);
    }

    /** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    }

    /** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	Robot.kClaw.armFlip(flipSpeed);
    }

    /** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
    protected boolean isFinished() {
        return isTimedOut();
    }

    /** Called once after {@code isFinished()} returns {@code true}. */
    protected void end() {
    	Robot.kClaw.armFlip(0.0);
    }

    /** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
    protected void interrupted() {
    	Robot.kClaw.armFlip(0.0);
    }
}
