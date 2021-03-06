package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class SameSideDoubleScaleAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public SameSideDoubleScaleAuton() {
		
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
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonTurn(46.5, 0.0175, 0.00012, 0.0, 1.25));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0));
			addParallel(new AutonIntake(-0.45, .5));
			addSequential(new AutonDrive(-.5, false, .4));
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonDelay(.75));
			//
			//// //Double Cube Commands
			addSequential(new AutonTurn(145, 0.0125, 0.0001, 0.0, 1.75)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.35, 1, 2.75, true));
			//// addSequential(new autonDefaultClaw());
			addParallel(new AutonIntake(0.9, 1.75));
			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .2));
			addParallel(new AutonMoveClaw(-0.75, 0.5));
			addParallel(new AutonSecondStageElevator(-25000, 500, 0.000217, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new AutonTurn(18.5, 0.0098, 0.000012, 0.0, 1.75));
			addSequential(new AutonSecondStageElevator());
			addSequential(new AutonDrive(
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 2.0,
							Robot.diameter, Robot.ticksPerRev),
					0.0005, 0.00006, 0.0, true));
			// addSequential(new autonOpenClaw());
			addSequential(new AutonIntake(-0.5, 1.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
		}
		else {
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
			addParallel(new AutonSecondStageElevator(-25004, 1000, 0.000217, 0.00000217, 0.0));
			addSequential(new AutonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonTurn(-48, 0.0175, 0.00012, 0.0, 1.75));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0));
			addParallel(new AutonIntake(-0.75, .5));
			addSequential(new AutonDrive(-.5, false, .45));
			addParallel(new AutonMoveClaw(1, 1.5));
			addSequential(new AutonDelay(.75));
			//
			//// //Double Cube Commands
			addSequential(new AutonTurn(-140, 0.011, 0.0001, 0.0, 1.75)); // p was 0.0095
			// addParallel(new autonIntake(0.6, 7.5));
			addSequential(new AutonLimitDrive(.35, 1, 2.75, true));
			//// addSequential(new autonDefaultClaw());
			// addParallel(new autonCloseClaw());
			addSequential(new AutonIntake(0.9, .45));
			addParallel(new AutonMoveClaw(-0.75, 0.5));
			addParallel(new AutonSecondStageElevator(-28000, 500, 0.000217, 0.00000317, 0.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new AutonTurn(-18.5, 0.0098, 0.000012, 0.0, 1.5));
			addSequential(new AutonSecondStageElevator());
			addSequential(new AutonDrive(
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.platformToNullZoneLeft) + 2.0,
							Robot.diameter, Robot.ticksPerRev),
					0.0005, 0.00006, 0.0, true));
			// addSequential(new autonOpenClaw());
			addSequential(new AutonIntake(-0.5, 1.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev) + 8,
					0.0005, 0.00006, 0.0, false));
		}
	}
}
