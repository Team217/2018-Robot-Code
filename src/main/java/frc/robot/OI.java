/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author ThunderChickens 217
 */
public class OI {
	
	public Joystick driver = new Joystick(RobotMap.driverPort);
	public Joystick oper = new Joystick(RobotMap.operPort);

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// Operator Buttons

	public Button squareOper = new JoystickButton(oper, RobotMap.buttonSquare);
	public Button circleOper = new JoystickButton(oper, RobotMap.buttonCircle);
	public Button triangleOper = new JoystickButton(oper, RobotMap.buttonTriangle);
	public Button xOper = new JoystickButton(oper, RobotMap.buttonX);
	public Button leftBumperOper = new JoystickButton(oper, RobotMap.leftBumper);
	public Button rightBumperOper = new JoystickButton(oper, RobotMap.rightBumper);
	public Button leftTriggerOper = new JoystickButton(oper, RobotMap.leftTrigger);
	public Button rightTriggerOper = new JoystickButton(oper, RobotMap.rightTrigger);
	public Button leftAnalogOper = new JoystickButton(oper, RobotMap.leftAnalog);

	// Driver Buttons

	public Button leftBumperDriver = new JoystickButton(driver, RobotMap.leftBumper);
	public Button circleDriver = new JoystickButton(driver, RobotMap.buttonCircle);
	public Button triangleDriver = new JoystickButton(driver, RobotMap.buttonTriangle);
	public Button squareDriver = new JoystickButton(driver, RobotMap.buttonSquare);
	public Button rightBumperDriver = new JoystickButton(driver, RobotMap.rightBumper);
	public Button rightTriggerDriver = new JoystickButton(driver, RobotMap.rightTrigger);
	public Button leftAnalogDriver = new JoystickButton(driver, RobotMap.leftAnalog);
	public Button leftTriggerDriver = new JoystickButton(driver, RobotMap.leftTrigger);
	public Button rightAnalogDriver = new JoystickButton(driver, RobotMap.rightAnalog);
	public Button touchPadDriver = new JoystickButton(driver, RobotMap.touchPad);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
