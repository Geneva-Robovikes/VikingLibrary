package frc.robot.commands;


import edu.wpi.first.wpilibj.command.CommandHelper;
import edu.wpi.first.wpilibj.command.SchedulerTestHelper;
import frc.robot.subsystems.HelloWorldSubsystem;
import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.ExecutionException;

/**
 * These are unit tests for the say hello command, which blinks an LED.
 */
public class SayHelloCommandUnitTest {
    // Fake out WPILib caring whether or not we have a roboRio.
//    static {
//        HLUsageReporting.SetImplementation(new HLUsageReporting.Null());
//    }

    // A command is typically executed by the WPILib scheduler. But in a unit test,
    // we really want to skinny down the number of dependencies we involve in doing 
    // the testing. And further, we want to control those dependencies so that our
    // tests don't fail because of a dependency change. So, I have implemented a fake
    // and very skinny command scheduler. Critics will say that the implementation
    // of that scheduler is woefully incomplete. Correct. It is that way on purpose.
    // We just need the ability to exercise our command in the most basic way possible.
    private SchedulerTestHelper scheduler;

    /**
     * Before a test starts, wire up the command scheduler to run
     * our scheduled command every 20 milliseconds
     * (what WPILib will do, I believe)
     */
    @Before
    public void setupSchedulerHelper() {
        scheduler = new SchedulerTestHelper(20);
    }

    /**
     * When a test finishes, tear down the scheduler, because it uses threading
     * to do its work, and if you do not tear it down, the test runner will never
     * terminate.
     */
    @After
    public void cleanUpSchedulerHelper() {
        scheduler.destroy();
    }

    /**
     * A test that checks that the command will flash the LED through the
     * hello world subsystem the requisite number of times once run
     * under the scheduler. Exceptions can be thrown by the scheduler
     * and if they are, then the test will fail. That's fine. One test
     * success criteria is defined by the Test decorator parameter that
     * says we should not exit the method with anything on the stack
     * (an exception, for example).
     * 
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test(expected = Test.None.class /* no exception expected */)
    public void itCanFlashMyLEDEvery200ms() throws InterruptedException, ExecutionException {
        // Assemble
        HelloWorldSubsystem mockHelloWorldSubsystem = mock(HelloWorldSubsystem.class);
        SayHelloCommand classUnderTest = new SayHelloCommand(mockHelloWorldSubsystem);

        // Act
        // Run our command for a given number of seconds. The forDuration
        // method call returns a 'Future', and as such is making an asynchronous
        // call, which returns immediately, before the command finishes. The get
        // method gets the return value from the command, which is nothing, but
        // more importantly, waits on the command to finish.
        scheduler.run(classUnderTest).forDuration(3).get();

        // Assert
        // A range is tested here because we are counting nano seconds, which is not deterministic.
        // There may be a way to test a range in one statement, but it was not immediately obvious.
        verify(mockHelloWorldSubsystem, atLeast(14)).toggleLED();
        verify(mockHelloWorldSubsystem, atMost(15)).toggleLED();
    }

    /**
     * A test that validates that the state of the LED is off after the command is
     * interrupted (because the scheduler stopped in a manner similar to how commands
     * will be interrupted within WPILib).
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test(expected = Test.None.class /* no exception expected */)
    public void itTurnsOffMyLEDWhenStopping() throws InterruptedException, ExecutionException {
        // Assemble
        HelloWorldSubsystem mockHelloWorldSubsystem = mock(HelloWorldSubsystem.class);
        SayHelloCommand classUnderTest = new SayHelloCommand(mockHelloWorldSubsystem);

        // Act
        // Run our command for a given number of seconds. The forDuration
        // method call returns a 'Future', and as such is making an asynchronous
        // call, which returns immediately, before the command finishes. The get
        // method gets the return value from the command, which is nothing, but
        // more importantly, waits on the command to finish.
        scheduler.run(classUnderTest).forDuration(1).get();

        // Assert
        // What we really want is a verification that the last call to the mock
        // is turning off the LED. This test only reports IF it was called...not when.
        // Hmmmm...
        verify(mockHelloWorldSubsystem, times(1)).turnOffLED();
    }

    /**
     * A test that verifies that the correct subsystems have been registered to the
     * command. This is mega important because this is how the WPILib scheduler knows
     * how to "preempt" a command already running with another command that is using
     * the same subsystem. Note that testing a commands' registered subsystems is a
     * protected method on the command. Therefore, I have created a cheat that exposes
     * that one method for the purpose of testing.
     */
    @Test
    public void itRegistersTheCorrectSubsystems() {
        // Assemble
        HelloWorldSubsystem mockHelloWorldSubsystem = mock(HelloWorldSubsystem.class);

        //Act
        SayHelloCommand classUnderTest = new SayHelloCommand(mockHelloWorldSubsystem);

        //Assert
        assertTrue(CommandHelper.doesRequire(classUnderTest, mockHelloWorldSubsystem));
    }
}
