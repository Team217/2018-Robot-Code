package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a scale auton when the scale is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class SameSideScaleAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a scale auton when the scale is on the same side as your position. */
	public SameSideScaleAuton() {
		
//		double shortDistance = EncoderConverter.inchToEnc(12.0, Robot.diameter, Robot.ticksPerRev);
//		double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToScale) 
//				- Robot.botLength - 12.0, Robot.diameter, Robot.ticksPerRev);

		if (Robot.balances.get(1).getIsLeftPad()) {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneLeft) + 
					0.5 * Robot.fieldMeasurements.get(Measurements.leftNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
			
			//Single Cube Commands
			addParallel(new AutonSecondStageElevator(-28004, 1000, 0.000217, 0.00000217, 0.0));
			
			addSequential(new AutonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.75, .75));
////			addSequential(new autonTurn(10.0, 0.03, 0.0003, 0.0));
////			addSequential(new autonDrive(shortDistance, 0.0005, 0.00006, 0.0));
			addSequential(new AutonTurn(87.5, 0.0114, 0.00012, 0.0, 2.25));
			addSequential(new AutonDelay(0.75));
			addSequential(new AutonIntake(-0.50, .5));
			addParallel(new AutonIntake(-0.50, .5));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0)); 
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-20.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
		}
		else {
			double distanceToScale = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToNullZoneRight) - 10 + 
					0.5 * Robot.fieldMeasurements.get(Measurements.rightNullZoneDepth), Robot.diameter, Robot.ticksPerRev);
		
			
			//Single Cube Commands
			addParallel(new AutonSecondStageElevator(-28004, 1000, 0.000217, 0.00000217, 0.0));
			
			addSequential(new AutonDrive(distanceToScale, 0.0004, 0.00006, 0.0, true));
			addParallel(new AutonMoveClaw(.75, .75));
////			addSequential(new autonTurn(10.0, 0.03, 0.0003, 0.0));
////			addSequential(new autonDrive(shortDistance, 0.0005, 0.00006, 0.0));
			addSequential(new AutonTurn(-87.5, 0.0114, 0.00012, 0.0, 2.25));
			addSequential(new AutonDelay(0.75));
			addSequential(new AutonIntake(-0.50, .5));
			addParallel(new AutonIntake(-0.50, .5));
			addParallel(new AutonSecondStageElevator(0.0, 500, 0.000157, 0.000005, 0.0)); 
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-20.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
		
		}
	}
}
