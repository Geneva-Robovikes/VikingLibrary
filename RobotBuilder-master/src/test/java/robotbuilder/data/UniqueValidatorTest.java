
package robotbuilder.data;

import org.junit.*;
import robotbuilder.MainFrame;
import robotbuilder.TestUtils;
import robotbuilder.extensions.Extensions;
import robotbuilder.robottree.RobotTree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author alex
 */
public class UniqueValidatorTest {

    public UniqueValidatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Extensions.init();
        MainFrame.getInstance();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test that the default setup is valid.
     */
    @Test
    public void testSimpleValidSetup() {
        RobotTree tree = TestUtils.getNewRobotTree();
        RobotComponent robot = tree.getRoot();
        RobotComponent subsystems = (RobotComponent) robot.getChildren().elementAt(0);

        // Create a basic subsystem
        RobotComponent subsystem = new RobotComponent("Subsystem", "Subsystem", tree);
        subsystems.add(subsystem);
        RobotComponent victor1 = new RobotComponent("Victor 1", "Speed Controller", tree);
        subsystem.add(victor1);
        RobotComponent victor2 = new RobotComponent("Victor 2", "Speed Controller", tree);
        subsystem.add(victor2);

        // Test it
        assertTrue("Victor 1 Output Channel (PWM) is not valid.",
                victor1.getProperty("Output Channel (PWM)").isValid());
        assertTrue("Victor 2 Output Channel (PWM) is not valid.",
                victor2.getProperty("Output Channel (PWM)").isValid());
    }

    /**
     * Make an invalid set up and test that it's invalid in the right way
     */
    @Test
    public void testSimpleInvalidSetup() {
        RobotTree tree = TestUtils.getNewRobotTree();
        RobotComponent robot = tree.getRoot();
        RobotComponent subsystems = (RobotComponent) robot.getChildren().elementAt(0);

        // Create a basic subsystem
        RobotComponent subsystem = new RobotComponent("Subsystem", "Subsystem", tree);
        subsystems.add(subsystem);
        RobotComponent victor1 = new RobotComponent("Victor 1", "Speed Controller", tree);
        subsystem.add(victor1);
        RobotComponent victor2 = new RobotComponent("Victor 2", "Speed Controller", tree);
        subsystem.add(victor2);
        victor2.getProperty("Output Channel (PWM)").setValueAndUpdate("0");

        // Test it
        assertTrue("Victor 1 Output Channel (PWM) is not valid.",
                victor1.getProperty("Output Channel (PWM)").isValid());
        assertFalse("Victor 2 Output Channel (PWM) should not be valid.",
                victor2.getProperty("Output Channel (PWM)").isValid());
    }

    /**
     * Check that it starts out valid, gets modified to the right invalid state,
     * then becomes valid again.
     */
    @Test
    public void testSimpleValidToggle() {
        RobotTree tree = TestUtils.getNewRobotTree();
        RobotComponent robot = tree.getRoot();
        RobotComponent subsystems = (RobotComponent) robot.getChildren().elementAt(0);

        // Create a basic subsystem
        RobotComponent subsystem = new RobotComponent("Subsystem", "Subsystem", tree);
        subsystems.add(subsystem);
        RobotComponent victor1 = new RobotComponent("Victor 1", "Speed Controller", tree);
        subsystem.add(victor1);
        RobotComponent victor2 = new RobotComponent("Victor 2", "Speed Controller", tree);
        subsystem.add(victor2);

        // Test it
        assertTrue("Victor 1 Output Channel (PWM) is not valid.",
                victor1.getProperty("Output Channel (PWM)").isValid());
        assertTrue("Victor 2 Output Channel (PWM) is not valid.",
                victor2.getProperty("Output Channel (PWM)").isValid());

        // Make it invalid
        victor2.getProperty("Output Channel (PWM)").setValueAndUpdate("0");

        // Test it
        assertTrue("Victor 1 Output Channel (PWM) is not valid.",
                victor1.getProperty("Output Channel (PWM)").isValid());
        assertFalse("Victor 2 Output Channel (PWM) should not be valid.",
                victor2.getProperty("Output Channel (PWM)").isValid());

        // Make it valid agoin
        victor2.getProperty("Output Channel (PWM)").setValueAndUpdate("1");

        // Test it
        assertTrue("Victor 1 Output Channel (PWM) is not valid.",
                victor1.getProperty("Output Channel (PWM)").isValid());
        assertTrue("Victor 2 Output Channel (PWM) is not valid.",
                victor2.getProperty("Output Channel (PWM)").isValid());
    }
}
