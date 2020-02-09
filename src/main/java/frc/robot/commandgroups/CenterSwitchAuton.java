package frc.robot.commandgroups;

import frc.robot.*;
import frc.robot.commands.auton.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a switch auton from the center position.
 * 
 * @author ThunderChickens 217
 */
public class CenterSwitchAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a switch auton from the center position. */
	public CenterSwitchAuton() {
		// requires(Robot.kDriveBase);
		// requires(Robot.kElevators);
		
		if (Robot.balances.get(0).getIsLeftPad()) {
			double horizontalDistance = EncoderConverter.inchToEnc(
					Robot.fieldMeasurements.get(Measurements.centerLineToLeftSwitchEdge)
							- Robot.fieldMeasurements.get(Measurements.exchangeRightToCenter),
					Robot.diameter, Robot.ticksPerRev); // - 6.0 may change if we need to
			
			// Hypotenuse of right triangle formed after the turn, that way we go far enough
			double distance = Math.hypot(horizontalDistance,
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.wallToSwitch) - Robot.botLength - 6.0,
							Robot.diameter, Robot.ticksPerRev));
			
			double angle = Robot.getBearingAngle(horizontalDistance, distance, false);
			System.out.println(angle);
			// One cube
			addSequential(new AutonTurn(-(angle + 2), 0.0255, 0.00015, 0.0, 1)); // turn angle, P, I, D - initial turn //p was 0.035 before changed at plant
			addParallel(new AutonSecondStageElevator(-16000.0, 100, 0.0000717, 0.00000117, 0.0)); // target, delay, P, I, D //recheck this value
			addSequential(new AutonDrive(distance, 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			addParallel(new AutonMoveClaw(0.3, 2.0)); // move claw down
			addSequential(new AutonTurn(0.0, 0.0180, 0.0005, 0.0, 1)); // turn angle, P, I, D - adjustment turn //p was 0.0305
			addSequential(new AutonDrive(0.5, false, .75)); // speed, timeout
			addParallel(new AutonOpenClaw()); // reverse intake at end
			addSequential(new AutonIntake(-0.35, .25)); // reverse intake at end
			addParallel(new AutonIntake(-0.35, .8)); // reverse intake at end
			addParallel(new AutonSecondStageElevator(0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-80.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
//				
//			// Two cube
			addParallel(new AutonMoveClaw(.65, 2.5)); // move claw down
			addSequential(new AutonTurn(55.5, 0.0115, 0.00005, 0.0, 1)); // turn angle, P, I, D - adjustment turn, //p was 0.022 // TODO: Remove a 0 fom I
			addSequential(new AutonLimitDrive(.6, 1, 1.45, true)); // target, position, P, I, D
			addParallel(new AutonIntake(.8, 1));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-50.0, Robot.diameter, Robot.ticksPerRev), 0.00055,
					0.000006, 0.0, false)); // target, position, P, I, D
			addSequential(new AutonTurn(0, 0.016, 0.0003, 0.0, 1.5)); // turn angle, P, I, D - initial turn //p was 0.015
			addParallel(new AutonSecondStageElevator(-18000.0, 100, 0.0000717, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(-0.5, .75)); // move claw up
			addSequential(new AutonDrive(0.65, true, 1.65)); // speed, timeout
			addSequential(new AutonIntake(.85, .1));
			addParallel(new AutonOpenClaw()); // reverse intake at end
			addParallel(new AutonSecondStageElevator(0, 250, 0.000052, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-70.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			//
			// //Three cube
			 //addSequential(new autonTurn(-54, 0.0086, 0.00005, 0.0, 1)); // turn angle, P, I, D - initial turn
			 addParallel(new AutonMoveClaw(0.75, 2.0)); // move claw down
			// addSequential(new autonDrive(EncoderConverter.inchToEnc(-85.0, Robot.diameter, Robot.ticksPerRev), 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			 addSequential(new AutonTurn(60, 0.0093, 0.00005, 0.0, 1)); // turn angle, P, I, D - adjustment turn
			addSequential(new AutonLimitDrive(.625, .8, 1.5, true)); // target, position, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-50.0, Robot.diameter, Robot.ticksPerRev), 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			// addParallel(new autonMoveClaw(-0.75, .75)); // move claw down
			 addSequential(new AutonTurn(0.0, 0.0095, 0.0003, 0.0, .75)); // turn angle, P, I, D - adjustment turn
			 addParallel(new AutonSecondStageElevator(-18000.0, 0.0000717, 0.00000117, 0.0)); // target, P, I, D
			 addSequential(new AutonDrive(0.75, true, 1)); // speed, timeout
			 addParallel(new AutonIntake(-0.35, 1)); // reverse intake at end
			// addParallel(new autonSecondStageElevator(0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			// addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
			
		}
		else {
			double horizontalDistance = EncoderConverter.inchToEnc(
					Robot.fieldMeasurements.get(Measurements.centerLineToRightSwitchEdge)
							+ Robot.fieldMeasurements.get(Measurements.exchangeRightToCenter) - Robot.botWidth - 6.0,
					Robot.diameter, Robot.ticksPerRev); // - 6.0 may change if we need to
			
			// Hypotenuse of right triangle formed after the turn, that way we go far enough
			double distance = Math.hypot(horizontalDistance,
					EncoderConverter.inchToEnc(
							Robot.fieldMeasurements.get(Measurements.wallToSwitch) - Robot.botLength - 12.0,
							Robot.diameter, Robot.ticksPerRev));
			
			double angle = Robot.getBearingAngle(horizontalDistance, distance, false);
			addSequential(new AutonTurn((angle + 2), 0.048, 0.00015, 0.0, 1)); // turn angle, P, I, D - initial turn //p was 0.035 before changed at plant
			addParallel(new AutonSecondStageElevator(-16000.0, 100, 0.0000717, 0.00000117, 0.0)); // target, delay, P, I, D //recheck this value
			addSequential(new AutonDrive(distance, 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			addParallel(new AutonMoveClaw(0.3, 2.0)); // move claw down
			addSequential(new AutonTurn(0.0, 0.021, 0.0005, 0.0, 1)); // turn angle, P, I, D - adjustment turn //p was 0.0305
			addSequential(new AutonDrive(0.5, false, .75)); // speed, timeout
			addParallel(new AutonOpenClaw()); // reverse intake at end
			addParallel(new AutonIntake(-0.35, .95)); // reverse intake at end
			addSequential(new AutonDelay(.4));
			addParallel(new AutonSecondStageElevator(0, 300, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-78.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
//				
//			// Two cube
			addParallel(new AutonMoveClaw(.65, 2.5)); // move claw down
			addSequential(new AutonTurn(-52.5, 0.01165, 0.00005, 0.0, 1)); // turn angle, P, I, D - adjustment turn, //p was 0.022 // TODO: Remove a 0 fom I
			addSequential(new AutonLimitDrive(.4, 1, 2.25, true)); // target, position, P, I, D
			addParallel(new AutonIntake(.8, 1));
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-50.0, Robot.diameter, Robot.ticksPerRev), 0.00055,
					0.000006, 0.0, false)); // target, position, P, I, D
			addSequential(new AutonTurn(0, 0.017, 0.0003, 0.0, 1.5)); // turn angle, P, I, D - initial turn //p was 0.015
			addParallel(new AutonSecondStageElevator(-18000.0, 100, 0.0000717, 0.00000117, 0.0)); // target, P, I, D
			addParallel(new AutonMoveClaw(-0.5, .75)); // move claw up
			addSequential(new AutonDrive(0.65, true, 1.65)); // speed, timeout
			addSequential(new AutonIntake(.5, .1));
			addParallel(new AutonOpenClaw()); // reverse intake at end
			addParallel(new AutonSecondStageElevator(0, 250, 0.000052, 0.00000117, 0.0)); // target, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-70.0, Robot.diameter, Robot.ticksPerRev), 0.0005,
					0.00006, 0.0, false));
			
			// //Three cube
			 //addSequential(new autonTurn(-54, 0.0086, 0.00005, 0.0, 1)); // turn angle, P, I, D - initial turn
			 addParallel(new AutonMoveClaw(0.75, 2.0)); // move claw down
			// addSequential(new autonDrive(EncoderConverter.inchToEnc(-85.0, Robot.diameter, Robot.ticksPerRev), 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			 addSequential(new AutonTurn(-60, 0.0093, 0.00005, 0.0, 1)); // turn angle, P, I, D - adjustment turn
			addSequential(new AutonLimitDrive(.625, .8, 1.5, true)); // target, position, P, I, D
			addSequential(new AutonDrive(EncoderConverter.inchToEnc(-50.0, Robot.diameter, Robot.ticksPerRev), 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
			// addParallel(new autonMoveClaw(-0.75, .75)); // move claw down
			 addSequential(new AutonTurn(0.0, 0.0095, 0.0003, 0.0, .75)); // turn angle, P, I, D - adjustment turn
			 addParallel(new AutonSecondStageElevator(-18000.0, 0.0000717, 0.00000117, 0.0)); // target, P, I, D
			 addSequential(new AutonDrive(0.75, true, 1)); // speed, timeout
			 addParallel(new AutonIntake(-0.35, 1)); // reverse intake at end
			// addParallel(new autonSecondStageElevator(0, 250, 0.0000517, 0.00000117, 0.0)); // target, P, I, D
			// addSequential(new autonDrive(EncoderConverter.inchToEnc(-24.0, Robot.diameter, Robot.ticksPerRev), 0.0005, 0.00006, 0.0, false));
			
		}
	}
}
