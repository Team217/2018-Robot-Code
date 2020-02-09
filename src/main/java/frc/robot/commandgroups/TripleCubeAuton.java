package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class TripleCubeAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public TripleCubeAuton() {
		
		// double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter, Robot.ticksPerRev);
		// double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale)
		// - Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);
		
		if (Robot.balances.get(1).getIsLeftPad()) {
			double distanceToScale = EncoderConverter.inchToEnc(
					2 + Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft), Robot.diameter,
					Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(
					1.75 + Math.hypot(
							(Robot.fieldMeasurements.get(Measurements.leftWallToSwitch)
									- Robot.fieldMeasurements.get(Measurements.leftWallToScale) - Robot.botLength),
							(Robot.fieldMeasurements.get(Measurements.switchToNullLeft)
									+ (.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0))
							- 3.0,
					Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.leftWallToSwitch)
									- Robot.fieldMeasurements.get(Measurements.leftWallToScale) + 22.0,
							Robot.diameter, Robot.ticksPerRev),
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.switchToNullLeft)
									+ (.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0,
							Robot.diameter, Robot.ticksPerRev),
					false);
			
			// Single Cube Commands
			addParallel(new AutonSecondStageElevator(-35004, 1000, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.65, .5));
			addSequential(new AutonTurn(46.5, 0.0175, 0.00012, 0.0, 1));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0));
			addParallel(new AutonIntake(-0.7, .5));
			addSequential(new AutonDrive(-.5, false, .55));
			addParallel(new AutonMoveClaw(.65, .5));
			addSequential(new AutonDelay(.35));
			//
			//// //Double Cube Commands
			addSequential(new AutonTurn(150, 0.010, 0.0001, 0.0, 1)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.5, .9, 2.75, true));
			//// addSequential(new autonDefaultClaw());
			addParallel(new AutonIntake(0.8, 1.75));
			// addParallel(new autonCloseClaw());
			// addSequential(new autonIntake(0.8, .4));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addParallel(new AutonSecondStageElevator(-16000.0, 0.0000917, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(-.65, .5));
			// addSequential(new autonDelay(0.25));
			addSequential(new AutonSecondStageElevator());
			// addSequential(new autonOpenClaw());
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(26.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.00055, 0.00006, 0.0, false));
			addSequential(new AutonIntake(-0.4, 1.0));
			// addParallel(new autonDefaultClaw());
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(.65, 1.5));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-48.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addSequential(new AutonTurn(140, 0.02, 0.0002, 0.0, 1));
			addSequential(new AutonLimitDrive(.5, .9, 2.75, true));
			addParallel(new AutonSecondStageElevator(-25004, 250, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-60.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addSequential(new AutonTurn(30, 0.0095, 0.00006, 0.0, 1));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(48.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addParallel(new AutonIntake(-0.35, .5));
		}
		else {
			double distanceToScale = EncoderConverter.inchToEnc(
					2 + Robot.fieldMeasurements.get(Measurements.wallToNullZoneRight) - 10
							+ 0.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth),
					Robot.diameter, Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(1.75
					+ Math.hypot(
							(Robot.fieldMeasurements.get(Measurements.rightWallToSwitch)
									- Robot.fieldMeasurements.get(Measurements.rightWallToScale) + 32.5),
							(Robot.fieldMeasurements.get(Measurements.switchToNullRight)
									+ (.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)) - 13.0))
					- 3.0, Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.rightWallToSwitch)
									- Robot.fieldMeasurements.get(Measurements.rightWallToScale) + 22.0,
							Robot.diameter, Robot.ticksPerRev),
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.switchToNullRight)
									+ (.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)) - 13.0,
							Robot.diameter, Robot.ticksPerRev),
					false);
			
			// Single Cube Commands
			addParallel(new AutonSecondStageElevator(-25004, 1000, 0.000217, 0.00000217, 0.0));
			
			addSequential(new AutonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.75, .75));
			//// addSequential(new autonTurn(10.0, 0.03, 0.0003, 0.0));
			//// addSequential(new autonDrive(shortDistance, 0.0005, 0.00006, 0.0));
			addSequential(new AutonTurn(-87.5, 0.0114, 0.00012, 0.0));
			addSequential(new AutonDelay(0.2));
			addSequential(new AutonSecondStageElevator());
			addParallel(new AutonIntake(-0.35, .5));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-20.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			
			//
			// //Double Cube Commands
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonTurn(-(angleToCube - 8.5), 0.0135, 0.0001, 0.0));
			addParallel(new AutonIntake(0.6, 7.5));
			addSequential(new AutonDrive(distanceToCube, 0.000575, 0.00006, 0.0, true));
			// addSequential(new autonDefaultClaw());
			// addSequential(new autonIntake(0.6, .4));
			addParallel(new AutonCloseClaw());
			addSequential(new AutonIntake(0.8, .4));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addParallel(new AutonSecondStageElevator(-15000.0, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(-1, .5));
			// addSequential(new autonDelay(0.25));
			addSequential(new AutonSecondStageElevator());
			// addSequential(new autonOpenClaw());
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(26.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.00055, 0.00006, 0.0, false));
			addSequential(new AutonIntake(-0.25, 1.0));
			addParallel(new AutonDefaultClaw());
			addParallel(new AutonSecondStageElevator(0.0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-48.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.00055, 0.00006, 0.0, false));
			addSequential(new AutonTurn(-140, 0.02, 0.0002, 0.0));
			addParallel(new AutonIntake(0.9, 7.5));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(52.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.00055, 0.00006, 0.0, false));
			addSequential(new AutonIntake(0.9, .1));
			addParallel(new AutonSecondStageElevator(-25004, 250, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-60.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addSequential(new AutonTurn(-30, 0.00925, 0.00006, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(48.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
			addParallel(new AutonIntake(-0.35, .5));
		}
	}
}
