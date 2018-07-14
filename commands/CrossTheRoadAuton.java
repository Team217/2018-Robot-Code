package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.EncoderConverter;
import org.usfirst.frc.team217.robot.Measurements;
import org.usfirst.frc.team217.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * A {@code CommandGroup} for a drive forward auton.
 * 
 * @author ThunderChickens 217
 */
public class CrossTheRoadAuton extends CommandGroup {
	
	/** A {@code CommandGroup} for a drive forward auton. */
    public CrossTheRoadAuton() {
    	
    	if(Robot.positionSelected.equals(Robot.center)) {
			double horizontalDistance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.centerLineToLeftSwitchEdge)
					- Robot.fieldMeasurements.get(Measurements.exchangeRightToCenter), Robot.diameter, Robot.ticksPerRev); // - 6.0 may change if we need to
			
			// Hypotenuse of right triangle formed after the turn, that way we go far enough
			double distance = Math.hypot(horizontalDistance, EncoderConverter.inchToEnc(
					Robot.fieldMeasurements.get(Measurements.wallToSwitch) - Robot.botLength - 6.0, Robot.diameter, Robot.ticksPerRev));
			
			double angle = Robot.getBearingAngle(horizontalDistance, distance, false);
			
//			addSequential(new autonTurn(-angle, 0.025, 0.000003, 0.0)); // turn angle, P, I, D - initial turn
			addSequential(new autonDrive(distance, 0.000475, 0.000006, 0.0, false)); // target, position, P, I, D
    	}
    	else {
    		double distance = EncoderConverter.inchToEnc(Robot.fieldMeasurements.get(Measurements.wallToSwitch) - Robot.botLength, Robot.diameter, Robot.ticksPerRev);

    		addSequential(new autonDrive(distance, 0.00035, 0.00006, 0.0, true));
    	}
    }
}