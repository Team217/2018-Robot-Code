package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.teleop.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for all teleop controls.
 * 
 * @author ThunderChickens 217
 */
public class TeleopGroup extends CommandGroup {
	
	/** A {@code CommandGroup} for a all teleop controls. */
	public TeleopGroup() {
		requires(Robot.kClaw);
		requires(Robot.kClimber);
		requires(Robot.kDriveBase);
		requires(Robot.kElevator);

		addParallel(new TeleopClawFlip());
		addParallel(new TeleopClimb());
		addParallel(new TeleopDriveBase());
		addParallel(new TeleopIntake());
//		addParallel(new teleopIntakeElevator());
		addParallel(new TeleopOpenClaw());
		addParallel(new TeleopSecondStageElevator());
		addParallel(new TeleopPTO());

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
