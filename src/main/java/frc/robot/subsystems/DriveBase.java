package frc.robot.subsystems;

import frc.robot.*;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Runs the bot's drivebase.
 * 
 * @author ThunderChickens 217
 */
public class DriveBase extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	WPI_TalonSRX rightMaster = RobotMap.rightMaster;
	WPI_VictorSPX rightMidSlave = RobotMap.rightMidSlave;
	WPI_VictorSPX rightBackSlave = RobotMap.rightBackSlave;
	WPI_TalonSRX leftMaster = RobotMap.leftMaster;
	WPI_VictorSPX leftMidSlave = RobotMap.leftMidSlave;
	WPI_VictorSPX leftBackSlave = RobotMap.leftBackSlave;
	PigeonIMU pigeon = RobotMap.pigeon;
	
	double pigeonAngle = 0.0;

	PID drivePID = RobotMap.drivePID;
	PID turnPID = RobotMap.turnPID;
	PID angleCorrectPID = new PID(0.01, 0.0, 0.0, 100);

	public boolean drivePID_OnTarget = false;
	public boolean turnPID_OnTarget = false;
	
	public double originalAngle = 0.0;
	
	/** The default command to run during initialization. */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		leftMaster.setup();
		rightMaster.setup();
		rightMidSlave.follow(rightMaster);
		rightBackSlave.follow(rightMaster);
		leftMidSlave.follow(leftMaster);
		leftBackSlave.follow(leftMaster);
		rightMaster.resetEncoder();
	}

	/**
	 * Drives the robot at the given forward and turn speeds.
	 * 
	 * @param speed
	 *           The forward/reverse drive speed
	 * @param turn
	 *           The turn drive speed
	 * @param antiTipOn
	 *           {@code true} if antitip should be enabled. Automatically disables if bot is in climb mode
	 */
	public void drive(double speed, double turn, boolean antiTipOn) {
		double leftSpeed = speed + turn;
		double rightSpeed = -speed + turn;
		
		if(antiTipOn && !Climber.currentPTO.equals(Climber.PTOMode.climbMode)) {
			double[] antiTipSpeed = antiTip(leftSpeed, rightSpeed);
			leftSpeed = antiTipSpeed[0];
			rightSpeed = antiTipSpeed[1];
		}
		
		leftMaster.set(leftSpeed);
		rightMaster.set(rightSpeed);
	}
	
	protected double[] antiTip(double leftSpeed, double rightSpeed) {
		if(pigeon.getPitch() >= 12.0) {
			leftSpeed = -0.5;
			rightSpeed = 0.5;
		}
		else if(pigeon.getPitch() <= -12.0) {
			leftSpeed = 0.5;
			rightSpeed = -0.5;
		}
		
		double[] speed = {leftSpeed, rightSpeed};
		return speed;
	}

	/**
	 * Drives the robot at the given forward and turn speeds.
	 * 
	 * @param speed
	 *           The forward/reverse drive speed
	 * @param turn
	 *           The turn drive speed
	 * @param antiTipOn
	 *           {@code true} if antitip should be enabled. Automatically disables if bot is in climb mode
	 */
	public void driveStraight(double speed, double turn) {
		double leftSpeed = speed + turn;
		double rightSpeed = -speed + turn;

		// Bot turning correction using the pigeon
		double angle = RobotMap.pigeon.getAngle();
		double angleError = angle - originalAngle;
		double angleCorrect = Math.abs(angleCorrectPID.getOutput(angle, originalAngle));

		if(angleCorrect > 1.0)
		{
			angleCorrect = 1.0;
		}

		if(speed > 0.0) {
			if(angleError > 0.0) {
				leftSpeed *= (1.0 - angleCorrect);
			}
			else if(angleError < 0.0) {
				rightSpeed *= (1.0 - angleCorrect);
			}
		}
		else {
			if(angleError > 0.0) {
				rightSpeed *= (1.0 - angleCorrect);
			}
			else if(angleError < 0.0) {
				leftSpeed *= (1.0 - angleCorrect);
			}
		}
		
		leftMaster.set(leftSpeed);
		rightMaster.set(rightSpeed);
	}
	
	/**
	 * Drives the bot forward to a certain encoder position using {@code PID}.
	 * 
	 * @param target
	 *         The target encoder count
	 * @param kP
	 *         The P value for {@code PID}
	 * @param kI
	 *         The I value for {@code PID}
	 * @param kD
	 *         The D value for {@code PID}
	 * @param turnCorrection
	 *         {@code true} if drive straight angle correction should be enabled
	 */
	public void driveForward(double target, double kP, double kI, double kD, boolean turnCorrection) {
		
		// Drive speed calculation
		drivePID.setPID(kP, kI, kD);
		
		double position = -rightMaster.getEncoder(); // Target is positive, so encoder needs to be positive, but defaults to negative
		
		double leftSpeed = drivePID.getOutput(position, target);
		double rightSpeed = drivePID.getOutput(position, target);
		
		if(leftSpeed > 1.0)
		{
			leftSpeed = 1.0;
		}
		else if(leftSpeed < -1.0)
		{
			leftSpeed = -1.0;
		}
		
		if(rightSpeed > 1.0)
		{
			rightSpeed = 1.0;
		}
		else if(rightSpeed < -1.0)
		{
			rightSpeed = -1.0;
		}
		
		if(turnCorrection) {
			// Bot turning correction using the pigeon
			double angle = RobotMap.pigeon.getAngle();
			double angleError = angle - originalAngle;
			double angleCorrect = Math.abs(angleCorrectPID.getOutput(angle, originalAngle));
			
			if(angleCorrect > 1.0)
			{
				angleCorrect = 1.0;
			}
			
			if(angleError > 0.0) {
				leftSpeed *= (1.0 - angleCorrect);
			}
			else if(angleError < 0.0) {
				rightSpeed *= (1.0 - angleCorrect);
			}
		}
		
		// Finished Check
		drivePID_OnTarget = false;
		
		if (position >= (target - 100)) {
			leftSpeed = 0.0;
			rightSpeed = 0.0;
			drivePID_OnTarget = true;
		}
		
		leftMaster.set(leftSpeed);
		rightMaster.set(-rightSpeed);
	}

	/**
	 * Drives the bot backwards to a certain encoder position using {@code PID}.
	 * 
	 * @param target
	 *         The target encoder count
	 * @param kP
	 *         The P value for {@code PID}
	 * @param kI
	 *         The I value for {@code PID}
	 * @param kD
	 *         The D value for {@code PID}
	 * @param turnCorrection
	 *         {@code true} if drive straight angle correction should be enabled
	 */
	public void driveBackward(double target, double kP, double kI, double kD, boolean turnCorrection) {
		
		// Drive speed calculation
		drivePID.setPID(kP, kI, kD);

		double position = -rightMaster.getEncoder(); // Target is positive, so encoder needs to be positive, but defaults to negative
		
		double leftSpeed = drivePID.getOutput(position, target);
		double rightSpeed = drivePID.getOutput(position, target);
		
		if(leftSpeed > 1.0)
		{
			leftSpeed = 1.0;
		}
		else if(leftSpeed < -1.0)
		{
			leftSpeed = -1.0;
		}
		
		if(rightSpeed > 1.0)
		{
			rightSpeed = 1.0;
		}
		else if(rightSpeed < -1.0)
		{
			rightSpeed = -1.0;
		}
		
		if(turnCorrection) {
			// Bot turning correction using the pigeon
			double angle = RobotMap.pigeon.getAngle();
			double angleError = angle - originalAngle;
			double angleCorrect = Math.abs(angleCorrectPID.getOutput(angle, originalAngle));
			
			if(angleCorrect > 1.0)
			{
				angleCorrect = 1.0;
			}
			
			if(angleError > 0.0) {
				rightSpeed *= (angleCorrect - 1.0);
			}
			else if(angleError < 0.0) {
				leftSpeed *= (angleCorrect - 1.0);
			}
		}
		
		// Finished Check
		drivePID_OnTarget = false;
		
		if (position <= (target + 100)) {
			leftSpeed = 0.0;
			rightSpeed = 0.0;
			drivePID_OnTarget = true;
		}
		
		leftMaster.set(leftSpeed);
		rightMaster.set(-rightSpeed);
	}
	
	/**
	 * Turns the bot to a certain angle using a {@code PigeonIMU} and {@code PID}.
	 * 
	 * @param turnAngle
	 *          The target angle
	 * @param isPosTurn
	 *          {@code true} if the bot should turn clockwise, {@code false} if the bot should turn counter-clockwise
	 * @param kP
	 *          The P value for {@code PID}
	 * @param kI
	 *          The I value for {@code PID}
	 * @param kD
	 *          The D value for {@code PID}
	 */
	public void autonTurn(double turnAngle, double kP, double kI, double kD) {
		turnPID.setPID(kP, kI, kD);
		pigeonAngle = pigeon.getAngle();
		
		double speed = turnPID.getOutput(pigeonAngle, turnAngle);
		
		turnPID_OnTarget = false;
		
		if(pigeonAngle >= (turnAngle - 2) && pigeonAngle <= (turnAngle + 2)) {
			speed = 0.0;
			turnPID_OnTarget = true;
		}
		
		leftMaster.set(speed);
		rightMaster.set(speed);
	}
}