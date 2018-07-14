package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that runs the intake lift in auton.
 * 
 * @author ThunderChickens 217
 * @deprecated
 *          We do not have an intake elevator anymore
 */
public class autonIntakeElevator extends Command {

	double target = 0;
	double position = 0;
	double kP = 0;
	double kI = 0;
	double kD = 0;
	
	/** @deprecated We do not have an intake elevator anymore */
	public autonIntakeElevator(double target1, double position1, double kP1, double kI1, double kD1) {
		super("autonIntakeElevator");
		requires(Robot.kElevator);
		target = target1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
		position = position1;
	}

	/** @deprecated We do not have an intake elevator anymore */
	protected void initialize() {
	}

	/** @deprecated We do not have an intake elevator anymore */
	protected void execute() {
	//	Robot.kElevators.autonIntakeElevator(position, target, kP, kI, kD);
	}

	/** @deprecated We do not have an intake elevator anymore */
	protected boolean isFinished() {
		return Robot.kElevator.intakePID_OnTarget;	
	}

	/** @deprecated We do not have an intake elevator anymore */
	protected void end() {
	}

	/** @deprecated We do not have an intake elevator anymore */
	protected void interrupted() {
	}
}
