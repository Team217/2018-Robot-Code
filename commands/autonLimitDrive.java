package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;
import org.usfirst.frc.team217.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class autonLimitDrive extends Command {

	double speed = 0;
	double time = 0;
	double intakeSpeed = 0;
	boolean turnCorrection = true;
	
    public autonLimitDrive(double speed1, double intakeSpeed1, double timeout, boolean gyroCorrectionEnabled) {
    	super("autonLimtDrive");
    	requires(Robot.kClaw);
    	requires(Robot.kDriveBase);
    	
    	speed = speed1;
    	intakeSpeed = intakeSpeed1;
    	turnCorrection = gyroCorrectionEnabled;
    	setTimeout(timeout);
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(turnCorrection) {
    		Robot.kDriveBase.driveStraight(speed, 0.0);
    	}
    	else {
    		Robot.kDriveBase.drive(speed, 0.0, false);
    	}
    	Robot.kClaw.intake(intakeSpeed);
    	Robot.kClaw.openClaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || !RobotMap.intakeLimitSwitch.get();
    }

    // Called once after isFinished returns true
    protected void end() {
       	Robot.kDriveBase.drive(0, 0, false);
    	Robot.kClaw.intake(0);
    	Robot.kClaw.defaultClaw();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
       	Robot.kDriveBase.drive(0, 0, false);
    	Robot.kClaw.intake(0);
    	Robot.kClaw.defaultClaw();
    }
}
