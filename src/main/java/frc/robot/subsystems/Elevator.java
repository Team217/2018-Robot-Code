package frc.robot.subsystems;

import frc.robot.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Runs the bot's elevator.
 * 
 * @author ThunderChickens 217
 */
public class Elevator extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	WPI_TalonSRX leftElevatorLift = RobotMap.leftElevatorLift;
	WPI_TalonSRX rightElevatorLift = RobotMap.rightElevatorLift;
	DigitalInput elevatorBottom = RobotMap.elevatorLimitSwitch;
	DigitalInput elevatorBottom2 = RobotMap.elevatorLimitSwitch2;
	DigitalInput elevatorTop = RobotMap.elevatorTopLimit;

	PID elevatorPID = RobotMap.elevatorPID;
	PID elevatorMaintainPID = RobotMap.elevatorMaintainPID;

	double elevatorSpeed = 0;
	double lastEncoder = 0;
	double elevatorMult = 1;
	
	public boolean intakePID_OnTarget = false;
	public boolean elevatorPID_OnTarget = false;
	
	/** The default command to run during initialization. */
	public void initDefaultCommand() {
		rightElevatorLift.setup();
		leftElevatorLift.setup();
	}
	
	/**
	 * Checks if the elevator's limit switches are hit and sets the elevator speed accordingly.
	 * 
	 * @param speed
	 *           The speed value currently being sent to the elevator motors
	 */
	public void elevatorLimitCheck(double speed) {
		if ((!elevatorBottom.get() || !elevatorBottom2.get()) && speed >= 0.0) {
			elevatorSpeed = 0;
			rightElevatorLift.resetEncoder();
			leftElevatorLift.resetEncoder();
			lastEncoder = 0;
		}
		else if(!elevatorTop.get() && speed <= 0.0) { // TODO: !elevatorTop.get() on the comp bot
			elevatorSpeed = -0.085;
		}
	}
	
	/**
	 * Runs the elevator at the given speed. Elevator does not fall and stops moving if it hits the elevator limit switches.
	 * 
	 * @param speed
	 *           The desired elevator speed
	 */
	public void secondStageElevator(double speed) {
		//elevatorMaintainPID.setPID(0.0001417, 0.00000117, 0);
		
		if (rightElevatorLift.getEncoder() <= -31000 && speed <= 0) {
			elevatorMult = .35;
		}
		else if(rightElevatorLift.getEncoder() >= -5000 && speed >= 0.0) {
			elevatorMult = .25;
		}
		else {
			elevatorMult = 1.0;
		}
		
		if (speed != 0) {
			lastEncoder = rightElevatorLift.getEncoder();
			elevatorSpeed = speed;
		}
		else if (speed == 0) {
			elevatorSpeed = -0.085;
			elevatorMult = 1.0;
		}
		
		elevatorLimitCheck(speed);
		rightElevatorLift.set(elevatorSpeed * elevatorMult);
		leftElevatorLift.set(elevatorSpeed * elevatorMult);
	}
	
	/**
	 * Drives the elevator to the desired encoder target using PID.
	 * 
	 * @param target
	 *          The target encoder count
	 * @param isUp
	 *          {@code true} if the elevator should go up, {@code false} if the elevator should go down
	 * @param kP
	 *          The P value for {@code PID}
	 * @param kI
	 *          The I value for {@code PID}
	 * @param kD
	 *          The D value for {@code PID}
	 */
	public void autonSecondElevator(double target, boolean isUp, double kP, double kI, double kD) {
		elevatorPID.setPID(kP, kI, kD);
		
		double position = rightElevatorLift.getEncoder();
		
		elevatorSpeed = elevatorPID.getOutput(position, target);
		elevatorMult = 1;
		if(elevatorSpeed >= 1.0) {
			elevatorSpeed = 1.0;
		}
		else if(elevatorSpeed <= -1.0) {
			elevatorSpeed = -1.0;
		}
		
		elevatorLimitCheck(elevatorSpeed);
		
		elevatorPID_OnTarget = false;
		
		if(isUp) {
			if(position < -000) {
			elevatorSpeed *= 0.85; // Slow down the elevator if going down so we don't slam into the bottom
			}
			if (position < (target + 1000)) {
				elevatorPID_OnTarget = true;
			}
		}
		else {
			
			if (position > (target - 1000)) {
				elevatorPID_OnTarget = true;
			}
		}
		if(rightElevatorLift.getEncoder() >= -3000 && target >= 0.0) {
			elevatorMult = .15;
		}
		
		leftElevatorLift.set(elevatorSpeed*elevatorMult);
		rightElevatorLift.set(elevatorSpeed*elevatorMult);
	}

}
