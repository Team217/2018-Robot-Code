package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class OppositeSideScaleSwitchAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public OppositeSideScaleSwitchAuton() {
		
//		double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter, Robot.ticksPerRev);
//		double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale) 
//				- Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);

		if (Robot.balances.get(1).getIsLeftPad()) {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneRight) + 
					0.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(Math.hypot((Robot.fieldMeasurements.get(
					Measurements.rightWallToSwitch) - Robot.fieldMeasurements.get(Measurements.rightWallToScale)), (Robot.fieldMeasurements.get(Measurements.switchToNullRight) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)) - 13.0)) - 3.0, Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(
					Measurements.rightWallToSwitch) - Robot.fieldMeasurements.get(Measurements.rightWallToScale) + 22.0, Robot.diameter, Robot.ticksPerRev), 
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.switchToNullRight) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth)) - 13.0, Robot.diameter, Robot.ticksPerRev), 
					false);
			double crossSwitch = EncoderConverter.inchToEnc(12 + Robot.botLength + Robot.fieldMeasurements.get(Measurements.scaleWidth) -6,
					+  Robot.diameter, Robot.ticksPerRev);
			double pastSwitch = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
					(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 34 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

			
			//Single Cube Commands
			addSequential(new autonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new autonTurn(-90.0, 0.012, 0.000003, 0.0, 1.5));
			addParallel(new autonSecondStageElevator(-25004, 1000, 0.0000517, 0.00000117, 0.0));
			addSequential(new autonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));	
			addParallel(new autonMoveClaw(0.5, 0.75));
			addSequential(new autonTurn(4, 0.01225, 0.000003, 0.0, 1.25));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addParallel(new autonIntake(-0.5, 1.0));
			addParallel(new autonSecondStageElevator(0.0, 250, 0.000117, 0.00000117, 0.0)); 
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
			
//			//Double Cube Commands
			addParallel(new autonMoveClaw(1, 2.0)); 
			addSequential(new autonTurn(160, 0.0079, 0.00001, 0.0, 1.75));
			addSequential(new autonDelay(.05));
			addSequential(new autonLimitDrive(.4, .8, 5, true)); // target, position, P, I, D
			addParallel(new autonCloseClaw());
			addSequential(new autonIntake(0.8, .4));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new autonSecondStageElevator(-16000, 0.000217, 0.00000317, 0.0));
			addParallel(new autonMoveClaw(-0.75, 0.5));
			//addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
//			addSequential(new autonOpenClaw());
			addSequential(new autonDrive(EncoderConverter.inchToEnc(36.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonIntake(-0.35, 1.0));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonDefaultClaw());
		}
		else {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft) + 
					0.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
			double distanceToCube = EncoderConverter.inchToEnc(Math.hypot((Robot.fieldMeasurements.get(
					Measurements.leftWallToSwitch) - Robot.fieldMeasurements.get(Measurements.leftWallToScale)), (Robot.fieldMeasurements.get(Measurements.switchToNullLeft) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0)) - 3.0, Robot.diameter, Robot.ticksPerRev);
			double angleToCube = 180 - Robot.getBearingAngle(EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(
					Measurements.leftWallToSwitch) - Robot.fieldMeasurements.get(Measurements.leftWallToScale) + 22.0, Robot.diameter, Robot.ticksPerRev), 
					EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.switchToNullLeft) + 
					(.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth)) - 13.0, Robot.diameter, Robot.ticksPerRev), 
					false);
			double crossSwitch = EncoderConverter.inchToEnc(12 + Robot.botLength + Robot.fieldMeasurements.get(Measurements.scaleWidth) -6,
					+  Robot.diameter, Robot.ticksPerRev);
			double pastSwitch = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
					(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 32 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

			
			//Single Cube Commands
			addSequential(new autonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new autonTurn(90.0, 0.012, 0.000003, 0.0, 1.75));
			addParallel(new autonSecondStageElevator(-25004, 1000, 0.0000517, 0.00000117, 0.0));
			addSequential(new autonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));	
			addParallel(new autonMoveClaw(0.5, 0.75));
			addSequential(new autonTurn(-4, 0.01225, 0.000003, 0.0, 1.25));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addParallel(new autonIntake(-0.5, 1.0));
			addParallel(new autonSecondStageElevator(0.0, 250, 0.000117, 0.00000117, 0.0)); 
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
			
//			//Double Cube Commands
			addParallel(new autonMoveClaw(1, 2.0)); 
			addSequential(new autonTurn(-160, 0.0079, 0.00001, 0.0, 1.75));
			addSequential(new autonDelay(.05));
			addSequential(new autonLimitDrive(.4, .8, 5, true)); // target, position, P, I, D
			addParallel(new autonCloseClaw());
			addSequential(new autonIntake(0.8, .4));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new autonSecondStageElevator(-16000, 0.000217, 0.00000317, 0.0));
			addParallel(new autonMoveClaw(-0.75, 0.5));
			//addSequential(new autonDelay(0.25));
			addSequential(new autonSecondStageElevator());
//			addSequential(new autonOpenClaw());
			addSequential(new autonDrive(EncoderConverter.inchToEnc(36.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonIntake(-0.35, 1.0));
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addSequential(new autonDefaultClaw());
			}
	}
}
