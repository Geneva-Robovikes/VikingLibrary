#header()

package ${package}.subsystems;
#set($subsystem = $helper.getByName($subsystem_name, $robot))

import ${package}.commands.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
#@autogenerated_code("imports", "")
#parse("${exporter_path}Subsystem-imports.java")
#end

/**
 *
 */
public class #class($subsystem.name) extends PIDSubsystem {

#@autogenerated_code("constants", "    ")
#parse("${exporter_path}Subsystem-constants.java")
#end

#@autogenerated_code("declarations", "    ")
#parse("${exporter_path}Subsystem-declarations.java")
#end

    // Initialize your subsystem here
    public #class($subsystem.name)() {
#@autogenerated_code("pid", "        ")
#parse("${exporter_path}PIDSubsystem-pid.java")
#end

#@autogenerated_code("constructors", "        ")
#parse("${exporter_path}Subsystem-constructors.java")
#end


        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    @Override
    public void initDefaultCommand() {
#@autogenerated_code("default_command", "        ")
#parse("${exporter_path}Subsystem-default_command.java")
#end

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;

#@autogenerated_code("source", "        ")
#parse("${exporter_path}PIDSubsystem-source.java")
#end
    }

    @Override
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);

#@autogenerated_code("output", "        ")
#parse("${exporter_path}PIDSubsystem-output.java")
#end
    }

#@autogenerated_code("cmdpidgetters", "    ")
#parse("${exporter_path}Subsystem-pidgetters.java")
#end

}