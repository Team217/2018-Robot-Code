package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a switch auton when the switch is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class SameSideSwitchAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a switch auton when the switch is on the same side as your position. */
	public SameSideSwitchAuton() {
		
		// Distance from wall to switch and halfway through the switch depth
		double distance = EncoderConverter.inchToEnc(
				6 + Robot.fieldMeasurements.get(Measurements.wallToSwitch)
						+ Robot.fieldMeasurements.get(Measurements.switchDepth) - Robot.botLength,
				Robot.diameter, Robot.ticksPerRev);
		
		if (Robot.balances.get(0).getIsLeftPad()) {
			addParallel(new autonSecondStageElevator(-16000.0, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(distance, 0.000445, 0.000006, 0.0, true)); // target, position, P, I, D
			addParallel(new autonMoveClaw(0.65, 2.0)); // move claw down
			addSequential(new autonTurn(90.0, 0.0105, 0.000003, 0.0, 1.5)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(0.5, false, 0.6)); // speed, timeout
			addSequential(new autonIntake(-0.35, 1.0)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
		}
		else {
			addParallel(new autonSecondStageElevator(-16000.0, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(distance, 0.000445, 0.000006, 0.0, true)); // target, position, P, I, D
			addParallel(new autonMoveClaw(0.65, 2.0)); // move claw down
			addSequential(new autonTurn(-90.0, 0.0105, 0.000003, 0.0)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(0.5, false, 0.6)); // speed, timeout
			addSequential(new autonIntake(-0.35, 1.0)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
		}
	}
}
