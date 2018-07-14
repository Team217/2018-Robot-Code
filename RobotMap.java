/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team217.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 * 
 * @author ThunderChickens 217
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	// Controllers
	public static final int driverPort = 0;
	public static final int operPort = 1;
	
	// Gyro
	public static final int gyro_ID = 0;
	
	// Motors/Solenoids
	public static final int rightMaster_ID = 3; // talon
	public static final int leftMaster_ID = 12; // talon
	public static final int rightMidSlave_ID = 2; // victor
	public static final int leftMidSlave_ID = 13; // victor
	public static final int rightBackSlave_ID = 1; // victor
	public static final int leftBackSlave_ID = 14; // victor
	public static final int intakeFlip_ID = 4; // talon
	public static final int leftElevatorLift_ID = 5; // talon
	public static final int rightElevatorLift_ID = 6; // talon
	public static final int leftIntake_ID = 8; // victor
	public static final int rightIntake_ID = 11; // victor
	public static final int driveBase_ID1 = 6; // double solenoid
	public static final int driveBase_ID2 = 1; // double solenoid
	public static final int shortArm_ID1 = 2; // double solenoid
	public static final int shortArm_ID2 = 5; // double solenoid
	public static final int longArm_ID1 = 3; // double solenoid
	public static final int longArm_ID2 = 4; // double solenoid
	public static final int climberUp_ID = 0; // solenoid
	public static final int climberSpool_ID = 7; // solenoid
	
	// Limit Switch Values
	public static final int intakeLiftLimitSwitch_ID = 1;
	public static final int intakeLimitSwitch_ID = 5;
	public static final int elevatorLimitSwitch_ID = 0;
	public static final int elevatorLimitSwitch2_ID = 2;
	public static final int elevatorTopLimit_ID = 8;
	
	// Drivebase Motors/Solenoids
	public static WPI_TalonSRX rightMaster = new WPI_TalonSRX(RobotMap.rightMaster_ID);
	public static WPI_TalonSRX leftMaster = new WPI_TalonSRX(RobotMap.leftMaster_ID);
	public static WPI_VictorSPX rightMidSlave = new WPI_VictorSPX(RobotMap.rightMidSlave_ID);
	public static WPI_VictorSPX leftMidSlave = new WPI_VictorSPX(RobotMap.leftMidSlave_ID);
	public static WPI_VictorSPX rightBackSlave = new WPI_VictorSPX(RobotMap.rightBackSlave_ID);
	public static WPI_VictorSPX leftBackSlave = new WPI_VictorSPX(RobotMap.leftBackSlave_ID);
	
	// Claw/Intake Motors/Solenoids
	public static WPI_TalonSRX intakeFlip = new WPI_TalonSRX(RobotMap.intakeFlip_ID);
	public static WPI_VictorSPX leftIntake = new WPI_VictorSPX(RobotMap.leftIntake_ID);
	public static WPI_VictorSPX rightIntake = new WPI_VictorSPX(RobotMap.rightIntake_ID);
	public static DoubleSolenoid shortArm = new DoubleSolenoid(1, RobotMap.shortArm_ID1, RobotMap.shortArm_ID2);
	public static DoubleSolenoid longArm = new DoubleSolenoid(1, RobotMap.longArm_ID1, RobotMap.longArm_ID2);
	
	// Elevator Things
	public static WPI_TalonSRX leftElevatorLift = new WPI_TalonSRX(RobotMap.leftElevatorLift_ID);
	public static WPI_TalonSRX rightElevatorLift = new WPI_TalonSRX(RobotMap.rightElevatorLift_ID);
	
	// Climber
	public static Solenoid climberUp = new Solenoid(RobotMap.climberUp_ID);
	public static Solenoid climberSpool = new Solenoid(RobotMap.climberSpool_ID); // keeps string from unraveling
	public static DoubleSolenoid driveBase = new DoubleSolenoid(RobotMap.driveBase_ID1, RobotMap.driveBase_ID2);
	
	// Limit Switches
	public static DigitalInput intakeLimitSwitch = new DigitalInput(RobotMap.intakeLimitSwitch_ID);
	public static DigitalInput intakeLiftLimitSwitch = new DigitalInput(RobotMap.intakeLiftLimitSwitch_ID);
	public static DigitalInput elevatorLimitSwitch = new DigitalInput(RobotMap.elevatorLimitSwitch_ID);
	public static DigitalInput elevatorLimitSwitch2 = new DigitalInput(RobotMap.elevatorLimitSwitch2_ID);
	public static DigitalInput elevatorTopLimit = new DigitalInput(RobotMap.elevatorTopLimit_ID);
	
	// gyro
	public static PigeonIMU pigeon = new PigeonIMU(RobotMap.gyro_ID);
	
	// PID
	public static PID drivePID = new PID(0.0, 0.0, 0.0, 100);
	public static PID turnPID = new PID(0.0, 0.0, 0.0, 100);
	public static PID intakePID = new PID(0.0, 0.0, 0.0, 100);
	public static PID elevatorPID = new PID(0.0, 0.0, 0.0, 100);
	public static PID elevatorMaintainPID = new PID(0.0, 0.0, 0.0, 100);
	
	// Power Distribution Panel
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	// Buttons
	public static final int buttonSquare = 1;
	public static final int buttonX = 2;
	public static final int buttonCircle = 3;
	public static final int buttonTriangle = 4;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int leftTrigger = 7;
	public static final int rightTrigger = 8;
	public static final int buttonOption = 10;
	public static final int leftAnalog = 11;
	public static final int rightAnalog = 12;
	public static final int touchPad = 14;
	
	// Axes
	public static final int X_Axis = 0;
	public static final int Y_Axis = 1;
	public static final int Z_Axis = 2;
	public static final int Z_RotateAxis = 5;
	
	// Pov
}
