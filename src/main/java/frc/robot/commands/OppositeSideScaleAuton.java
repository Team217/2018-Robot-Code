package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the opposite side of your position.
 * 
 * @author ThunderChickens 217
 */
public class OppositeSideScaleAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the opposite side of your position. */
    public OppositeSideScaleAuton() {
		
//		double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter, Robot.ticksPerRev);
//		double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale) 
//				- Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);
		double firstDistance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
				(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 28 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

		double secondDistance = EncoderConverter.inchToEnc(9 + Robot.fieldMeasurements.get(Measurements.scaleWidth),
				+  Robot.diameter, Robot.ticksPerRev);

		double crossSwitch = EncoderConverter.inchToEnc(10+ Robot.botLength + Robot.fieldMeasurements.get(Measurements.scaleWidth) -6,
				+  Robot.diameter, Robot.ticksPerRev);
		double pastSwitch = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
				(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 38 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

		
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
			
			//Single Cube Commands
			addSequential(new autonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new autonTurn(-90.0, 0.012, 0.000003, 0.0, 1.5));
			addParallel(new autonSecondStageElevator(-16004, 1000, 0.0000517, 0.00000117, 0.0));
			addSequential(new autonDrive(crossSwitch*.66, 0.0004, 0.00006, 0.0, true));	
//			addParallel(new autonMoveClaw(0.5, 0.75));
//			addSequential(new autonTurn(4, 0.01225, 0.000003, 0.0, 1.25));
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
//			addParallel(new autonIntake(-0.3, 1.0));
//			addSequential(new autonOpenClaw());
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
			
		}
		else {
			addSequential(new autonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new autonTurn(90.0, 0.012, 0.000003, 0.0, 1.5));
			addParallel(new autonSecondStageElevator(-16004, 1000, 0.0000517, 0.00000117, 0.0));
			addSequential(new autonDrive(crossSwitch*.66, 0.0004, 0.00006, 0.0, true));	
//			addParallel(new autonMoveClaw(0.5, 0.75));
//			addSequential(new autonTurn(0.0, 0.012, 0.000003, 0.0));
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(42.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, true));
//			addSequential(new autonTurn(-7.5, 0.04, 0.000003, 0.0,1));
//			addParallel(new autonIntake(-0.1, 1.0));
//			addSequential(new autonOpenClaw());
//			addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));

		}
    	
    }
}
