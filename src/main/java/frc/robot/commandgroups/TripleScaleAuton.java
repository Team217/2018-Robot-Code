package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side
 * as your position.
 * 
 * @author ThunderChickens 217
 */
public class TripleScaleAuton extends CommandGroup {

	/**
	 * A {@code CommandGroup} for a scale auton when the scale is on the same side
	 * as your position.
	 */
	public TripleScaleAuton() {

		// double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter,
		// Robot.ticksPerRev);
		// double distance =
		// EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale)
		// - Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);

		if (Robot.balances.get(1).getIsLeftPad()) {
			double distanceToScale = EncoderConverter.inchToEnc(
					Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft), Robot.diameter, Robot.ticksPerRev);
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
			addParallel(new AutonSecondStageElevator(-27004, 1000, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(distanceToScale, 0.0005, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.65, 1.5));
			addSequential(new AutonTurn(38.5, 0.0265, 0.00012, 0.0, 1.25));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.00005, 0.000008, 0.0));
			addParallel(new AutonIntake(-0.375, .75));
			addSequential(new AutonDrive(-.5, false, .4));
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonDelay(.75));
			
			
			//// //Double Cube Commands
			addSequential(new AutonTurn(152.5, 0.01075, 0.00025, 0.0, 1.75)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.4, 1, 2.75, true));
			//// addSequential(new autonDefaultClaw());
			addParallel(new AutonIntake(0.9, 1.75));
			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .2));
			addParallel(new AutonMoveClaw(-0.65, 0.5));
			addParallel(new AutonSecondStageElevator(-27500, 500, 0.000317, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new AutonTurn(24, 0.0098, 0.000012, 0.0, 1.75));
			addSequential(new AutonSecondStageElevator());
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 5.0,
					Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, true));
			// addSequential(new autonOpenClaw());
			addParallel(new AutonIntake(-0.375, 1.5));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
////
////			// Triple cube
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.00005, 0.000008, 0.0));
//			addParallel(new autonIntake(-0.45, .5));
//			addSequential(new autonDrive(-.5, false, .4));
			addParallel(new AutonMoveClaw(.65, 1.5));
			addSequential(new AutonDelay(.75));
			addSequential(new AutonTurn(137.5, 0.0111225, 0.00025, 0.0, 1.75)); // p was 0.0095
//			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.35, 1, 2.0, true));
//			//// addSequential(new autonDefaultClaw());
//			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .2));
			addParallel(new AutonIntake(0.9, .5));
			addParallel(new AutonMoveClaw(-0.75, 0.5));
			addParallel(new AutonSecondStageElevator(-25000, 500, 0.000317, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
//			addSequential(new autonTurn(18, 0.0098, 0.000012, 0.0, 1.75));
//			addSequential(new autonSecondStageElevator());
//			addSequential(new autonDrive(
//					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 8.0,
//							Robot.diameter, Robot.ticksPerRev),
//					0.0005, 0.00006, 0.0, true));
//			// addSequential(new autonOpenClaw());
//			addParallel(new autonIntake(-0.65, 1.0));
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
//					0.0005, 0.00006, 0.0, false));
		} else {
			double distanceToScale = EncoderConverter.inchToEnc(
					Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft), Robot.diameter, Robot.ticksPerRev);
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
			addParallel(new AutonSecondStageElevator(-25004, 1000, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(distanceToScale, 0.0005, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.65, 1.5));
			addSequential(new AutonTurn(-45, 0.023, 0.00012, 0.0, 1.25));
			addParallel(new AutonSecondStageElevator(0.0, 1000, 0.00005, 0.000008, 0.0));
			addParallel(new AutonIntake(-0.5, .5));
			addSequential(new AutonDrive(-.375, false, .65));
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonDelay(.75));
			//
			// //Double Cube Commands
			addSequential(new AutonTurn(-142.5, 0.01325, 0.00025, 0.0, 1.75)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.4, 1, 2.75, true));
			//// addSequential(new autonDefaultClaw());
			addParallel(new AutonIntake(0.9, 1.75));
			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .2));
			addParallel(new AutonMoveClaw(-0.75, 0.5));
			addParallel(new AutonSecondStageElevator(-25000, 500, 0.000317, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new AutonTurn(-20, 0.0115, 0.000012, 0.0, 1.75));
			addSequential(new AutonSecondStageElevator());
			addSequential(new AutonDrive(
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 5.0,
							Robot.diameter, Robot.ticksPerRev),
					0.0005, 0.00006, 0.0, true));
			// addSequential(new autonOpenClaw());
			addParallel(new AutonIntake(-0.35, 1.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
//
//			// Triple cube
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.00005, 0.000008, 0.0));
			addParallel(new AutonIntake(-0.45, .5));
			addSequential(new AutonDrive(-.5, false, .4));
			addParallel(new AutonMoveClaw(.65, 1.5));
			addSequential(new AutonDelay(.75));
			addSequential(new AutonTurn(-120, 0.0115, 0.0001, 0.0, 1.75)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.6, 1, 2.0, true));
			//// addSequential(new autonDefaultClaw());
			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .2));
			addParallel(new AutonIntake(0.9, .5));
			addParallel(new AutonMoveClaw(-0.75, 0.5));
		//	addParallel(new autonSecondStageElevator(-25000, 500, 0.000317, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-45.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
//			addSequential(new autonTurn(-18, 0.0098, 0.000012, 0.0, 1.75));
//			addSequential(new autonSecondStageElevator());
//			addSequential(new autonDrive(
//					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 8.0,
//							Robot.diameter, Robot.ticksPerRev),
//					0.0005, 0.00006, 0.0, true));
//			// addSequential(new autonOpenClaw());
//			addParallel(new autonIntake(-0.65, 1.0));
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
//					0.0005, 0.00006, 0.0, false));
		}
	}
}
