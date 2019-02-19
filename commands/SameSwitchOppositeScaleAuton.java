package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a switch auton when the switch is on the same side as your position.
 * 
 * @author ThunderChickens 217
 */
public class SameSwitchOppositeScaleAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a switch auton when the switch is on the same side as your position. */
	public SameSwitchOppositeScaleAuton() {
		
		// Distance from wall to switch and halfway through the switch depth
		double distance = EncoderConverter.inchToEnc(
				Robot.fieldMeasurements.get(Measurements.wallToSwitch)
						+ Robot.fieldMeasurements.get(Measurements.switchDepth) - Robot.botLength,
				Robot.diameter, Robot.ticksPerRev);
		double crossSwitch = EncoderConverter.inchToEnc(
				21 + Robot.fieldMeasurements.get(Measurements.scaleWidth) - Robot.botLength, +Robot.diameter,
				Robot.ticksPerRev);
		
		if (Robot.balances.get(0).getIsLeftPad()) {
			addParallel(new autonSecondStageElevator(-16000.0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(distance, 0.000445, 0.000006, 0.0, true)); // target, position, P, I, D
			addParallel(new autonMoveClaw(0.65, 2.0)); // move claw down
			addSequential(new autonTurn(90.0, 0.0105, 0.000003, 0.0, 1.75)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(0.5, false, 0.5)); // speed, timeout
			addSequential(new autonIntake(-0.45, .2)); // reverse intake at end
			addParallel(new autonSecondStageElevator(0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new autonTurn(30.0, 0.0085, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonMoveClaw(0.65, 1.0)); // move claw down
			addSequential(new autonDrive(EncoderConverter.inchToEnc(100.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonTurn(158.0, 0.00595, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonIntake(0.9, 2.0)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(24.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonIntake(0.9, .2)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-14.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonTurn(90.0, 0.0075, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonSecondStageElevator(-25004, 1000, 0.0000517, 0.00000117, 0.0));
			addParallel(new autonMoveClaw(-1, .65)); // move claw down
			addSequential(new autonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));
			addSequential(new autonTurn(-5, 0.0095, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(EncoderConverter.inchToEnc(35.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, true));
			addSequential(new autonIntake(-0.35, .4)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-35.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			
		}
		else {
			addParallel(new autonSecondStageElevator(-16000.0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(distance, 0.000445, 0.000006, 0.0, true)); // target, position, P, I, D
			addParallel(new autonMoveClaw(0.65, 2.0)); // move claw down
			addSequential(new autonTurn(-90.0, 0.0105, 0.000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(0.5, false, 0.5)); // speed, timeout
			addSequential(new autonIntake(-0.45, .2)); // reverse intake at end
			addParallel(new autonSecondStageElevator(0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-36.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			addSequential(new autonTurn(-30.0, 0.0085, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonMoveClaw(0.65, 1.0)); // move claw down
			addSequential(new autonDrive(EncoderConverter.inchToEnc(100.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonTurn(158.0, 0.00595, 0.0000003, 0.0, 1.75)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonIntake(0.9, 2.0)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(24.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonIntake(0.9, .2)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-14.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
			addSequential(new autonTurn(-90.0, 0.0075, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addParallel(new autonSecondStageElevator(-25004, 1000, 0.0000517, 0.00000117, 0.0));
			addParallel(new autonMoveClaw(-1, .65)); // move claw down
			addSequential(new autonDrive(crossSwitch, 0.0004, 0.00006, 0.0, true));
			addSequential(new autonTurn(5, 0.0095, 0.0000003, 0.0, 1.25)); // turn angle, P, I, D - adjustment turn
			addSequential(new autonDrive(EncoderConverter.inchToEnc(35.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, true));
			addSequential(new autonIntake(-0.35, .4)); // reverse intake at end
			addSequential(new autonDrive(EncoderConverter.inchToEnc(-35.0, Robot.diameter, Robot.ticksPerRev), 0.00065,
					0.00006, 0.0, false));
		}
	}
}
