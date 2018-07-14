/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team217.robot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.usfirst.frc.team217.robot.commands.CenterSwitchAuton;
import org.usfirst.frc.team217.robot.commands.CrossTheRoadAuton;
import org.usfirst.frc.team217.robot.commands.OppositeSideDoubleScaleAuton;
import org.usfirst.frc.team217.robot.commands.SameSideScaleAuton;
import org.usfirst.frc.team217.robot.commands.SameSideSwitchAuton;
import org.usfirst.frc.team217.robot.commands.TeleopCommands;
import org.usfirst.frc.team217.robot.commands.TripleScaleAuton;
import org.usfirst.frc.team217.robot.subsystems.Claw;
import org.usfirst.frc.team217.robot.subsystems.Climber;
import org.usfirst.frc.team217.robot.subsystems.DriveBase;
import org.usfirst.frc.team217.robot.subsystems.Elevator;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team217.robot.commands.ExampleCommand;
//import org.usfirst.frc.team217.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the TimedRobot documentation. If you change the name of this class or the
 * package after creating this project, you must also update the build.properties file in the project.
 * 
 * @author ThunderChickens 217
 */
public class Robot extends TimedRobot {
	// public static final ExampleSubsystem kExampleSubsystem = new
	// ExampleSubsystem();
	public static final Claw kClaw = new Claw();
	public static final Climber kClimber = new Climber();
	public static final DriveBase kDriveBase = new DriveBase();
	public static final Elevator kElevator = new Elevator();
	public static OI m_oi;
	
	public static ArrayList<Balance> balances = new ArrayList<Balance>();
	
	Command autonomousCommand;
	Command teleopCommand;
	
	public static String positionSelected, sideSelected, autoSelected;
	
	public static final String switchAuton = "Switch";
	public static final String scaleAuton = "Scale";
	public static final String doubleSwitchAuton = "Double Switch";
	public static final String doubleScaleAuton = "Double Scale";
	public static final String switchScaleAuton = "Switch Scale";
	public static final String crossRoadAuton = "Cross The Road";
	
	public static final String left = "Left";
	public static final String center = "Center";
	public static final String right = "Right";
	
	public static final String redSide = "Red Side";
	public static final String blueSide = "Blue Side";
	
	public static final String crossing = "Cross";
	public static final String disableCrossing = "Disable Crossing";
	
	SendableChooser<String> auton = new SendableChooser<>();
	SendableChooser<String> position = new SendableChooser<>();
	SendableChooser<String> side = new SendableChooser<>();
	SendableChooser<String> cross = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		
		autoSelected = auton.getSelected();
		positionSelected = position.getSelected();
		sideSelected = side.getSelected();
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(20, 20);
		camera.setFPS(30);
		
		SmartDashboard.putData("Auton Selection", auton);
		auton.addDefault("Cross The Line", crossRoadAuton);
		auton.addObject("Switch Auton", switchAuton);
		auton.addObject("Scale Auton", scaleAuton);
		// auton.addObject("Double Switch Auton", doubleSwitchAuton);
		// auton.addObject("Double Scale Auton", doubleScaleAuton);
		// auton.addObject("Switch Scale Auton", switchScaleAuton);
		
		// Allows drive team to select which position to run
		SmartDashboard.putData("Position Selection", position);
		position.addDefault("Center", center);
		position.addObject("Left", left);
		position.addObject("Right", right);
		
		// Allows drive team to select which side our alliance is
		SmartDashboard.putData("Side Selection", side);
		side.addDefault("Red Side", redSide);
		side.addObject("Blue Side", blueSide);
		
		SmartDashboard.putData("Crossover", cross);
		cross.addDefault("Cross", crossing);
		cross.addObject("Do Not Cross", disableCrossing);
		
		System.out.println("you boofed");
		try {
			Measurements.saveToJSON();
			readMeasurements();
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem information you want to clear when the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}
	
	/**
	 * Gets the FMS switch/scale data and adds it to the balances {@code ArrayList}.
	 * 
	 * @param fmsCode
	 *        The FMS string
	 */
	public static void parseGameData(String fmsCode) {
		System.out.println("FMS Data: " + fmsCode);
		SmartDashboard.putString("FMS Data", fmsCode);
		
		balances.clear();
		for (int i = 0; i < fmsCode.length(); i++) {
			balances.add(new Balance(i));
		}
	}
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		RobotMap.pigeon.reset();
		RobotMap.rightMaster.resetEncoder();
		RobotMap.leftMaster.resetEncoder();
	}
	
	// Measurements HashMaps
	HashMap<String, Double> redMeasurements = new HashMap<String, Double>();
	HashMap<String, Double> blueMeasurements = new HashMap<String, Double>();
	public static HashMap<String, Double> fieldMeasurements = new HashMap<String, Double>();
	
	// Bot measurements
	public static final double ticksPerRev = 4096.0 / 8.0; // The drivebase gear ratio is 8:1, or 1 motor rotation per 8
															// wheel rotations
	public static final double diameter = 6.375;
	public static final double botLength = 39.0;
	public static final double botWidth = 33.0;
	
	/**
	 * Reads the field measurements from the JSON files and saves them to the {@code redMeasurements} and {@code blueMeasurements} {@code HashMaps}.
	 */
	void readMeasurements() throws IOException, ParseException {
		HashMap<String, HashMap<String, Double>> fieldMeasurements = Measurements.getMeasurements();
		
		redMeasurements = fieldMeasurements.get(Measurements.redTable);
		blueMeasurements = fieldMeasurements.get(Measurements.blueTable);
	}
	
	/**
	 * Returns the bearing angle (off the positive y-axis) theta formed by the right triangle with legs x and y.
	 * 
	 * @param x
	 *        The length of the right triangle's leg parallel to the x-axis
	 * @param y
	 *        The length of the right triangle's leg parallel to the y-axis
	 * @param inRadians
	 *        {@code true} returns the angle in radians, {@code false} returns the angle in degrees
	 */
	public static double getBearingAngle(double x, double y, boolean inRadians) {
		double radians = Math.atan2(x, y); // Angle in radians from pi to -pi
		double degrees = radians * 180.0 / Math.PI; // Convert to degrees
		
		if (inRadians) {
			return radians;
		}
		return degrees;
	}
	
	/**
	 * This autonomous (along with the choose r code above) shows how to select between different autonomous modes using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you
	 * prefer the LabVIEW Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example) or additional comparisons to the switch structure below with additional
	 * strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		kDriveBase.originalAngle = 0.0;
		
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		if (teleopCommand != null) {
			teleopCommand.cancel();
		}
		
		// Reset the encoders/gyro
		RobotMap.rightMaster.resetEncoder();
		RobotMap.rightElevatorLift.resetEncoder();
		RobotMap.pigeon.reset();
		
		// Get selected auton options
		autoSelected = auton.getSelected();
		positionSelected = position.getSelected();
		sideSelected = side.getSelected();
		cross.getSelected();
		// Get field measurements
		try {
			Measurements.saveToJSON();
			readMeasurements();
		}
		catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		if (sideSelected.equals(blueSide)) {
			fieldMeasurements = blueMeasurements;
		}
		else {
			fieldMeasurements = redMeasurements;
		}
		
		// Check for Field Fault
		boolean arcadeFault = false;
		boolean crossEnabled = (cross.getSelected() == crossing);
		try {
			arcadeFault = FMS.isArcadeFault();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Choose auton
		parseGameData(DriverStation.getInstance().getGameSpecificMessage());
		System.out.println(crossEnabled);
		if (arcadeFault) {
			autonomousCommand = new CrossTheRoadAuton(); // Drive straight if arcade fault detected
			System.out.println("Warning: Arcade Fault detected. See the log for more details.");
		}
		else {
			if (crossEnabled) {
				// Booleans, true when the switch/scale is on the same side as your position
				boolean isSameSideSwitch = (positionSelected.equals(left) && balances.get(0).getIsLeftPad())
						|| (positionSelected.equals(right) && !balances.get(0).getIsLeftPad());
				boolean isSameSideScale = (positionSelected.equals(left) && balances.get(1).getIsLeftPad())
						|| (positionSelected.equals(right) && !balances.get(1).getIsLeftPad());
				switch (autoSelected) {
					case switchAuton:
						if (positionSelected.equals(center)) {
							autonomousCommand = new CenterSwitchAuton();
						}
						else if (isSameSideSwitch) {
							autonomousCommand = new SameSideSwitchAuton();
						}
						break;
					// case doubleSwitchAuton:
					// break;
					case scaleAuton:
						if (isSameSideScale && isSameSideSwitch) {
							autonomousCommand = new TripleScaleAuton();
						}
						else if (!isSameSideScale && !isSameSideSwitch) {
							autonomousCommand = new OppositeSideDoubleScaleAuton();
						}
						else if (!isSameSideScale && isSameSideSwitch) {
							autonomousCommand = new OppositeSideDoubleScaleAuton();
						}
						else {
							autonomousCommand = new TripleScaleAuton();
						}
						break;
					// case switchScaleAuton:
					// if(isSameSideScale && isSameSideSwitch) {
					// autonomousCommand = new SameSideScaleSwitchAuton();
					// }
					// else if(!isSameSideScale && !isSameSideSwitch) {
					//// autonomousCommand = new OppositeSideScaleSwitchAuton();
					// }
					// break;
					case crossRoadAuton:
						autonomousCommand = new CrossTheRoadAuton();
						break;
					default:
						break;
				}
			}
			else {
				boolean isSameSideSwitch = (positionSelected.equals(left) && balances.get(0).getIsLeftPad())
						|| (positionSelected.equals(right) && !balances.get(0).getIsLeftPad());
				boolean isSameSideScale = (positionSelected.equals(left) && balances.get(1).getIsLeftPad())
						|| (positionSelected.equals(right) && !balances.get(1).getIsLeftPad());
				switch (autoSelected) {
					case switchAuton:
						if (positionSelected.equals(center)) {
							autonomousCommand = new CenterSwitchAuton();
						}
						else if (isSameSideSwitch) {
							autonomousCommand = new SameSideSwitchAuton();
						}
						break;
					// case doubleSwitchAuton:
					// break;
					case scaleAuton:
						if (isSameSideScale && isSameSideSwitch) {
							autonomousCommand = new SameSideScaleAuton();
						}
						else if (!isSameSideScale && isSameSideSwitch) {
							autonomousCommand = new SameSideSwitchAuton();
						}
						else if (!isSameSideScale && !isSameSideSwitch) {
							autonomousCommand = new CrossTheRoadAuton();
						}
						else {
							autonomousCommand = new SameSideScaleAuton();
						}
						break;
					case crossRoadAuton:
						autonomousCommand = new CrossTheRoadAuton();
						break;
					default:
						break;
				}
			}
			
			kClimber.driveMode();
			
			if (autonomousCommand != null) {
				autonomousCommand.start();
			}
			else {
				autonomousCommand = new CrossTheRoadAuton();
				autonomousCommand.start();
			}
		}
	}
	
	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		smartDashboard();
		Scheduler.getInstance().run();
	}
	
	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		
		RobotMap.pigeon.reset();
		
		teleopCommand = new TeleopCommands();
		if (teleopCommand != null) {
			teleopCommand.start();
			System.out.println("went in if");
		}
		
	}
	
	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		smartDashboard();
		Scheduler.getInstance().run();
	}
	
	/**
	 * Checks if the joystick is in a given deadband and, if it is, sets the joystick value to 0.
	 * 
	 * @param value
	 *        The joystick value
	 * @param nullZone
	 *        The deadband size
	 * @return The joystick value after checking if it is in the deadband
	 */
	public static double deadband(double value, double deadband) {
		if (Math.abs(value) <= Math.abs(deadband)) {
			value = 0.0;
		}
		
		return value;
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	/** Puts bot data onto SmartDashboard. */
	public void smartDashboard() {
		SmartDashboard.putNumber("Pigeon Angle", RobotMap.pigeon.getAngle());
		SmartDashboard.putNumber("Pigeon Pitch", RobotMap.pigeon.getPitch());
		SmartDashboard.putNumber("Left Encoder", RobotMap.leftMaster.getEncoder());
		SmartDashboard.putNumber("Right Encoder", RobotMap.rightMaster.getEncoder());
		
		// drivebase enc negative when forward
		// intake lift enc negative when up
		// SmartDashboard.putNumber("Left Intake Lift Encoder",
		// leftIntakeLift.getEncoder());
		// SmartDashboard.putNumber("Right Intake Lift Encoder",
		// rightIntakeLift.getEncoder());
		SmartDashboard.putNumber("Left Elevator Lift Encoder", RobotMap.leftElevatorLift.getEncoder());
		SmartDashboard.putNumber("Right Elevator Lift Encoder", RobotMap.rightElevatorLift.getEncoder());
		
		SmartDashboard.putBoolean("Intake Flip Limit Fwd", RobotMap.intakeFlip.getLimitFwd());
		SmartDashboard.putBoolean("Intake Flip Limit Rev", RobotMap.intakeFlip.getLimitRev());
		
		SmartDashboard.putBoolean("Intake Limit", RobotMap.intakeLimitSwitch.get());
		SmartDashboard.putBoolean("Elevator Lift Limit", RobotMap.elevatorLimitSwitch.get());
		SmartDashboard.putBoolean("Elevator Top Limit", RobotMap.elevatorTopLimit.get());
		SmartDashboard.putBoolean("Elevator Lift Limit 2", RobotMap.elevatorLimitSwitch2.get());
		
	}
}
