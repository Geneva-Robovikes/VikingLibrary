package edu.wpi.first.wpilibj.command;

/**
 * Useful helper methods to make automated testing of commands easier.
 */
public class CommandHelper {
    /**
     * Expose out the doesRequire method so that we can test whether we required
     * the appropriate subsystems within a command. A static method is fine because
     * we never need to do anything other than call this method on an already
     * created command.
     * 
     * @param command       The command to check
     * @param subsystem     The subsystem to check
     * @return              True if the subsystem was required by the command
     */
    public static boolean doesRequire(Command command, Subsystem subsystem) {
        return command.doesRequire(subsystem);
    }
}