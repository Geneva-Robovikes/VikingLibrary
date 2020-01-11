/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.commands.SayHelloCommand;
import frc.robot.subsystems.HelloWorldSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * This is a typical hello world type robot program that flashes an LED when a button
 * is pressed on a joystick. 
 * 
 * This project is based on the CommandRobot VSCode template, lovingly called the
 * UntestableCommandRobot. This template has been altered to enable you to test
 * all commands and subsystems. Let's call this one TestableCommandRobot. 
 * Well, almost. Note the the Robot class itself is not *yet* testable. Stay tuned...
 * When you run "./gradlew build", marvel at the test task output as it runs your
 * code without a roboRio.
 */
public class Robot extends TimedRobot {
  // Declare hardware
  private final DigitalOutput led;
  // Declare int
  private int number;
  // Declare subsystems
  private final HelloWorldSubsystem helloWorldSubsystem;
  // Declare commands
  private final SayHelloCommand sayHelloCommand;
  // Declare command groups
  //   nothing to declare
  // Declare operator interface
  private final Joystick stick; 
  private final Button button;

  private Command m_autonomousCommand;
  private SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * Example testable command robot. This will flash a hello world led on digital IO
   * port 0 when the first button is held down on the first joysitck found. This robot
   * class itself is not yet testable, but all commands and subsystems are.
   */
  public Robot() {
    super();
    this.led = new DigitalOutput(RobotMap.led);
    this.number = 0;
    this.helloWorldSubsystem = new HelloWorldSubsystem(led, number);
    this.sayHelloCommand = new SayHelloCommand(helloWorldSubsystem);
    this.stick = new Joystick(RobotMap.joystickPort);
    this.button = new JoystickButton(stick, RobotMap.buttonNumber);
  }

  /**
   * Instead of using the OI class in the original template, simply add a
   * method here to contain all that logic.
   */
  public void wireUpOperatorInterface() {
    button.whileHeld(sayHelloCommand);
  }

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    wireUpOperatorInterface();
    m_chooser.setDefaultOption("Default Auto", sayHelloCommand);
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
