package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for all teleop controls.
 * 
 * @author ThunderChickens 217
 */
public class TeleopCommands extends CommandGroup {
	
	/** A {@code CommandGroup} for a all teleop controls. */
	public TeleopCommands() {
		requires(Robot.kClaw);
		requires(Robot.kClimber);
		requires(Robot.kDriveBase);
		requires(Robot.kElevator);

		addParallel(new teleopClawFlip());
		addParallel(new teleopClimb());
		addParallel(new teleopDriveBase());
		addParallel(new teleopIntake());
//		addParallel(new teleopIntakeElevator());
		addParallel(new teleopOpenClaw());
		addParallel(new teleopSecondStageElevator());
		addParallel(new teleopPTO());

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
