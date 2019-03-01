package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class SameSideScaleSwitchAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public SameSideScaleSwitchAuton() {
		
//		double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter, Robot.ticksPerRev);
//		double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale) 
//				- Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);

		if (Robot.balances.get(1).getIsLeftPad()) {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft) + 
					0.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(Math.hypot((Robot.fieldMeasurements.get(
					Measurements.leftWallToSwitch) - Robot.fieldMeasurements.get(Measurements.leftWallToScale) + 32.5), (Robot.fieldMeasurements.get(Measurements.switchToNullLeft) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0)) - 3.0, Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(
					Measurements.leftWallToSwitch) - Robot.fieldMeasurements.get(Measurements.leftWallToScale) + 22.0, Robot.diameter, Robot.ticksPerRev), 
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.switchToNullLeft) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0, Robot.diameter, Robot.ticksPerRev), 
					false);
			
			//Single Cube Commands
			addParallel(new autonSecondStageElevator(-33004, 1000, 0.000217, 0.00000217, 0.0));
			
			addSequential(new autonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new autonMoveClaw(.75, .75));
////			addSequential(new autonTurn(10.0, 0.03, 0.0003, 0.0));
////			addSequential(new autonDrive(shortDistance, 0.0005, 0.00006, 0.0));
			addSequential(new autonTurn(87.5, 0.01135, 0.00012, 0.0));
			addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
			addParallel(new autonIntake(-0.35, .5));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-22.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
			
//			
//			//Double Cube Commands
			addParallel(new autonSecondStageElevator(0.0, 0.000117, 0.00000117, 0.0)); 
			addParallel(new autonMoveClaw(0.6, 1.0)); 
			addSequential(new autonTurn(angleToCube-5, 0.0125, 0.0001, 0.0));
			addParallel(new autonIntake(0.6, 7.5));
			addSequential(new autonDrive(distanceToCube, 0.000575, 0.00006, 0.0, true));
//			addSequential(new autonDefaultClaw());
//			addSequential(new autonIntake(0.6, .4));
			addParallel(new autonCloseClaw());
			addSequential(new autonIntake(0.8, .4));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new autonSecondStageElevator(-25000, 0.000117, 0.00000317, 0.0));
			addParallel(new autonMoveClaw(-0.75, 0.5));
			//addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
//			addSequential(new autonOpenClaw());
			addSequential(new autonDrive(EncoderConverter.inchToEnc(24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonIntake(-0.25, 1.0));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonDefaultClaw());

		}
		else {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneRight) + 
					0.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(Math.hypot((Robot.fieldMeasurements.get(
					Measurements.rightWallToSwitch) - 24.0), (Robot.fieldMeasurements.get(Measurements.switchToNullRight) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)) - 11.0)) - 10.0, Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(
					Measurements.rightWallToSwitch) - 24.0, Robot.diameter, Robot.ticksPerRev), 
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.switchToNullLeft) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)), Robot.diameter, Robot.ticksPerRev), false);
			
			//Single Cube Commands
			addParallel(new autonSecondStageElevator(-33004, 1000, 0.000217, 0.00000217, 0.0));
			
			addSequential(new autonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new autonMoveClaw(.75, .75));
////			addSequential(new autonTurn(10.0, 0.03, 0.0003, 0.0));
////			addSequential(new autonDrive(shortDistance, 0.0005, 0.00006, 0.0));
			addSequential(new autonTurn(-87.5, 0.01135, 0.00012, 0.0, 1.25));
			addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
			addParallel(new autonIntake(-0.35, .5));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-22.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
			
//			
//			//Double Cube Commands
			addParallel(new autonSecondStageElevator(0.0, 0.000117, 0.00000117, 0.0)); 
			addParallel(new autonMoveClaw(0.6, 1.0)); 
			addSequential(new autonTurn(angleToCube+5, 0.0125, 0.0001, 0.0, 1.25));
			addParallel(new autonIntake(0.6, 7.5));
			addSequential(new autonDrive(distanceToCube, 0.000575, 0.00006, 0.0, true));
//			addSequential(new autonDefaultClaw());
//			addSequential(new autonIntake(0.6, .4));
			addParallel(new autonCloseClaw());
			addSequential(new autonIntake(0.8, .4));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new autonSecondStageElevator(-25000, 0.000117, 0.00000317, 0.0));
			addParallel(new autonMoveClaw(-0.75, 0.5));
			//addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
//			addSequential(new autonOpenClaw());
			addSequential(new autonDrive(EncoderConverter.inchToEnc(24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonIntake(-0.25, 1.0));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonDefaultClaw());		}
	}
}
