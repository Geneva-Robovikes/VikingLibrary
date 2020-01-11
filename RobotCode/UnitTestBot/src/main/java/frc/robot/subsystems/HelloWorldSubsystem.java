/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem which encapsuates an LED.
 */
public class HelloWorldSubsystem extends Subsystem {

  /**
   * LED example hardware that the subsystem encapsulates. We keep it private
   * because the user of the class should have no business fooling with
   * internal state directly. Use instance methods to perform actions on hardware.
   * We make it final because the instance needs to count on the fact that the
   * hardware will not change out from under it throughout its life.
   */
  private final DigitalOutput led;
  private int number;

  /**
   * We create a constructor to inject "dependencies" into our class. Do not instantiate
   * external dependencies directly. Instead, invite them in and simply use them. In this
   * way, we can always instantiate this class, even if hardware does not actually exist.
   * The requirement of instantiation is an "invariant". In order to even have the hope
   * of automated testing, we must always be able to instantiate our class.
   * @param led
   */
  public HelloWorldSubsystem(DigitalOutput led, int number) {

    // Another invariant is that our class always assumes that a
    // valid reference to our LED will exist. So complain 
    // if our invited guest is nothingness.
    if (led == null) {
      throw new IllegalArgumentException("I must have what at least looks like an LED!");
    }



    // Assign our invited guest to an instance variable, so we can use it later.
    this.led = led;
    this.number = number;
  }
  public HelloWorldSubsystem(DigitalOutput led) {
    // Another invariant is that our class always assumes that a
    // valid reference to our LED will exist. So complain
    // if our invited guest is nothingness.
    if (led == null) {
      throw new IllegalArgumentException("I must have what at least looks like an LED!");
    }
    this.led = led;
  }

  /**
   * Be really, really, really restrictive and deliberate.
   * Remember our valid reference to an LED invariant?
   * This makes it so we do not break that invariant. So this constructor is not allowed.
   * You COULD create the hardware here as a convenience for your user, but it results
   * in an untestable method. Confucious say: if you can't test it, it should not exist.
   * Now, technically you do not need to do this. If you have any other constructor on
   * your class, then Java will not create a default parameterless constructor. But this would
   * tell another developer...hey, I did this deliberately. Don't add one.
   */
  public HelloWorldSubsystem() {
    throw new IllegalAccessError("There is only one way to construct me. Pass in my dependencies please.");
  }


  /**
   * Instance method to interact with our hardware...turning on LED.
   */
  public void turnOnLED() {
    if (number == 5)
        led.set(true);
  }

  /**
   * Instance method to interact with our hardware...turning off LED.
   */
  public void turnOffLED() {
    led.set(false);
  }

  /**
   * Instance method to flip the state of our LED.
   */
  public void toggleLED() {
    led.set(!led.get());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    // NOTE: Do not do as this comment suggests. If you want to 
    // wire up a default command, pass in that command as a dependency
    // in the constructor and then set it here. Keep your classes testable
    // by decoupling dependencies. Instantiate dependencies on the outside
    // and pass them in.
  }
}
