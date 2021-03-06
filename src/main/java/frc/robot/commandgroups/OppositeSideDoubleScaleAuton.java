package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class OppositeSideDoubleScaleAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public OppositeSideDoubleScaleAuton() {
		
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
			double crossSwitch = EncoderConverter.inchToEnc(12+ Robot.botLength + Robot.fieldMeasurements.get(Measurements.scaleWidth),
					+  Robot.diameter, Robot.ticksPerRev);
			double pastSwitch = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
					(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 35 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

			
			//Single Cube Commands
			addSequential(new AutonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new AutonTurn(-90.0, 0.01275, 0.000003, 0.0, 1.75));
			addParallel(new AutonSecondStageElevator(-30004, 500, 0.0000517, 0.00000117, 0.0));
			addSequential(new AutonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));	
			addParallel(new AutonMoveClaw(0.5, 0.3));
			addSequential(new AutonTurn(5, 0.011, 0.000003, 0.0, 1));
			addSequential(new AutonDelay(.85));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addSequential(new AutonIntake(-.4, 1));
			addParallel(new AutonIntake(-.7, 1.0));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000117, 0.00000117, 0.0)); 
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
			
//			//Double Cube Commands
			addParallel(new AutonMoveClaw(1, 2.0)); 
			addSequential(new AutonTurn(153.5, 0.0125, 0.00001, 0.0, 1.75));
			addSequential(new AutonDelay(.05));
			addSequential(new AutonLimitDrive(.4, 1, 1.75, true)); // target, position, P, I, D
			addSequential(new AutonIntake(0.8, .4));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-17.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new AutonSecondStageElevator(-30000, 125, 0.000217, 0.00000317, 0.0));
			addSequential(new AutonTurn(18, 0.00835, 0.000003, 0.0, 1.25));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(52.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addParallel(new AutonIntake(-0.5, 1.0));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
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
			double crossSwitch = EncoderConverter.inchToEnc(10+ Robot.botLength + Robot.fieldMeasurements.get(Measurements.scaleWidth) -6,
					+  Robot.diameter, Robot.ticksPerRev);
			double pastSwitch = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) + Robot.fieldMeasurements.get(Measurements.switchDepth) +  
					(0.5 * Robot.fieldMeasurements.get(Measurements.switchToPlatform) + 32 - Robot.botLength ), Robot.diameter, Robot.ticksPerRev);

			
			//Single Cube Commands
			addSequential(new AutonDrive(pastSwitch, 0.0008, 0.00008, 0.0, true));
			addSequential(new AutonTurn(90.0, 0.0122, 0.000003, 0.0, 1.75));
			addParallel(new AutonSecondStageElevator(-30004, 500, 0.0000517, 0.00000117, 0.0));
			addSequential(new AutonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));	
			addParallel(new AutonMoveClaw(0.5, 0.4));
			addSequential(new AutonTurn(-6, 0.01265, 0.000003, 0.0, 1.25));
			addSequential(new AutonDelay(.75));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addSequential(new AutonIntake(-.4, 1.00));
			addParallel(new AutonSecondStageElevator(0.0, 650, 0.000117, 0.00000117, 0.0)); 
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-30.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));			
			
//			//Double Cube Commands
			addParallel(new AutonMoveClaw(1, 2.0)); 
			addSequential(new AutonTurn(-150, 0.0088, 0.00001, 0.0, 1.25));
			addSequential(new AutonDelay(.05));
			addSequential(new AutonLimitDrive(.5, 1, 2.25, true)); // target, position, P, I, D
			addParallel(new AutonDefaultClaw());
			addSequential(new AutonIntake(0.8, .4));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-12.0, Robot.diameter, Robot.ticksPerRev)+8, 0.0005, 0.00006, 0.0, false));
			addParallel(new AutonMoveClaw(-0.75, 0.4));
			addParallel(new AutonSecondStageElevator(-30000, 125, 0.000217, 0.00000317, 0.0));
			addSequential(new AutonTurn(-4, 0.01, 0.000003, 0.0, 1.75));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(45.0, Robot.diameter, Robot.ticksPerRev), 0.00055, 0.00006, 0.0, true));
			addParallel(new AutonIntake(-0.2, 1.0));
			addSequential(new AutonOpenClaw());
			}
	}
}
