package frc.robot.commands;


import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.SchedulerTestHelper;
import frc.robot.subsystems.HelloWorldSubsystem;
import org.junit.*;
import static org.mockito.Mockito.*;
import java.util.concurrent.ExecutionException;

/**
 * Integration tests that make sure that when our command acts on a subsystem,
 * that the hardware within the subsystem does what we expect. It is called
 * integration because we are testing the integration of classes together
 * to see if they act on each other as expected.
 */
public class SayHelloCommandIntegrationTest {
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
     * A test that checks that the command will turn on the LED within the
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
        // Because I can mock the hardware, I don't need a roboRio, and because
        // the subsystem can be passed its external dependencies, it does not
        // care that the hardware is not real.
        DigitalOutput ledMock = mock(DigitalOutput.class);
        int number = 0;
        // Note that we do not mock the subsystem because we are going to
        // test the result on the subsystem by exercising behavior on the command.
        HelloWorldSubsystem classUnderTest = new HelloWorldSubsystem(ledMock, number);
        SayHelloCommand sayHelloCommand = new SayHelloCommand(classUnderTest);

        // Act
        scheduler.run(sayHelloCommand).forDuration(3).get();

        // Assert
        verify(ledMock, atLeast(7)).set(true);
    }
}