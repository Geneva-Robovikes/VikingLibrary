package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalOutput;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Unit tests for the hello world subsystem. It consists of a simple
 * LED to demonstrate how you might go about unit testing your
 * subsystems.
 */
public class HelloWorldSubsystemUnitTest {
    // Fake out WPILib caring whether or not we have a roboRio.
//    static {
//        HLUsageReporting.SetImplementation(new HLUsageReporting.Null());
//    }

    /**
     * A test to make sure no one has cross-wired dependencies such
     * that our class is no longer testable.
     */
    @Test(expected = Test.None.class /* no exception expected */)
    public void itShouldInstantiateGivenAnLED() {
        // Assemble
        DigitalOutput mockLED = mock(DigitalOutput.class);
        int number = 0;

        // Act
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(mockLED, number);

        // Assert
        // Our test decorator contains the assertion
    }

    /**
     * A test to make sure that if a sneaky user tries to give us
     * a null LED, we do not let them.
     */
    @Test(expected = IllegalArgumentException.class)
    public void itShouldNotInstantiateWithANullLED() {
        // Assemble
        // Nothing to do

        // Act
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(null, 5);

        // Assert
        // Our test decorator contains the assertion
    }

    /**
     * A test to make sure that the user knows that a parameterless constructor
     * is verboden.
     */
    @Test(expected = IllegalAccessError.class)
    public void itShouldNotInstantiateWithNoLED() {
        // Assemble
        // Nothing to do

        // Act
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem();

        // Assert
        // Our test decorator contains the assertion
    }

    /**
     * A test to make sure we have set the state of the led digital output to true.
     */
    @Test
    public void itShouldTurnOnTheLEDIfNumberIsFive() {
        // Assemble
        DigitalOutput mockLED = mock(DigitalOutput.class);
        int number = 0;

        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(mockLED, number);

        // Act
        classUnderTest.turnOnLED();

        // Assert
        // Make sure that we have set the digital output to true
        //      only when the GYRO ANGLE == 5.
        // This may seem useless, but for the purpose of this test, we don't
        // care whether the DigitalOutput actually works (we assume it does).
        // We just want to make sure we have done our job by setting the state
        // properly.
        verify(number = 5);
        verify(mockLED, times(1)).set(true);
        //verify(mockGyro,  )
    }

    /**
     * A test to make sure we have set the state of the led digital output to false.
     */
    @Test
    public void itShouldTurnOffTheLED() {
        // Assemble
        DigitalOutput mockLED = mock(DigitalOutput.class);
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(mockLED);

        // Act
        classUnderTest.turnOffLED();
        
        // Assert
        // Make sure that we have set the digital output to false.
        // This may seem useless, but for the purpose of this test, we don't
        // care whether the DigitalOutput actually works (we assume it does).
        // We just want to make sure we have done our job by setting the state
        // properly.
        verify(mockLED, times(1)).set(false);
    }

    /**
     * A test to make sure we can toggle the state of the LED.
     */
    @Test
    public void itShouldToggleTheLED() {
        // Assemble
        DigitalOutput mockLED = mock(DigitalOutput.class);
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(mockLED);
        // Wire up some state so we know what our digital IO port will return.
        // Note that you can stack the return state when you matcher is called
        // more than once.
        when(mockLED.get()).thenReturn(false).thenReturn(true);

        // Act
        classUnderTest.toggleLED();
        
        // Assert
        // Make sure that we have set the digital output to the opposite
        // of the state that it is currently at, as defined by the when matcher above.
        verify(mockLED, times(1)).set(true);

        // Act, again...I normally don't do this in one test, but I wanted to demonstrate you can
        // stack state easily on when matchers.
        classUnderTest.toggleLED();
        
        // Assert
        // Make sure that we have set the digital output to the opposite
        // of the state that it is currently at, as defined by the when matcher above.
        verify(mockLED, times(1)).set(false);
    }

    /**
     * A test to make sure we know what port the LED is plugged into.
     * You could use this as a workflow/nag for notifying your electrical
     * subteam that a change has been made...you could even send an email
     * or text. That would be fancy.
     * 
     * NOTE: This does not test whether DigitalOutput in the ROBOT class
     * uses the correct constant. That would be in the unit test for that
     * class, assuming it is testable...
     */
    @Test
    public void itShouldReferencePort0ForLED() {
        // Assemble
        // Nothing to do

        // Act
        // Nothing to do

        // Assert
        assertEquals("Tell electrical that the LED port has changed, and then update this test.", 0, RobotMap.led);
    }
}