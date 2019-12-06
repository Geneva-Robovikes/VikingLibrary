package edu.wpi.first.wpilibj.command;

import java.util.concurrent.*;

/**
 * Implement a very skinny command runner that enables easy unit test of a command.
 */
public class SchedulerTestHelper {
    private Command commandToRun = null;
    private int heartbeatMilliseconds = 0;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Default constructor initialized runner heartbeat to 20 ms
     */
    public SchedulerTestHelper() {
        heartbeatMilliseconds = 20;
    }

    /**
     * Initialized a runner with your own heartbeat (in ms)
     */
    public SchedulerTestHelper(int heartbeatMilliseconds) {
        this.heartbeatMilliseconds = heartbeatMilliseconds;
    }

    /**
     * Do not let an instance to this class go to the garbage collector
     * without calling the destroy method first. It will hang your application
     * until the instance is garbage collected due to the use of the executor service.
     */
    public void destroy() {
        executor.shutdown();
    }

    /**
     * Run a command. Use the duration method to say how long.
     * @param commandToRun  Inherited WPILib command to run
     * @return              This test helper so you can chain forDuration.
     */
    public SchedulerTestHelper run(Command commandToRun) {
        this.commandToRun = commandToRun;
        return this;
    }

    /**
     * Make an asynchronous call to kick off the scheduler. We do it this way
     * so that you can interrupt the Future that is returned, in case you want
     * to unit test your command getting interrupted (which is common).
     * @param seconds   Number of seconds to run your command
     * @return          A Future that can be awaited for scheduler completion or cancelled
     * @throws InterruptedException
     */
    public Future<?> forDuration(int seconds) throws InterruptedException {
        if (commandToRun == null) {
            throw new IllegalStateException("You must run a command.");
        }
        return executor.submit(() -> {
            try {
                long start = System.nanoTime();
                commandToRun.initialize();
                boolean interrupted = Thread.currentThread().isInterrupted();
                while (!commandToRun.isFinished() && !interrupted && (System.nanoTime() < (start + TimeUnit.SECONDS.toNanos(seconds)))) {
                    commandToRun.execute();
                    Thread.sleep(heartbeatMilliseconds);
                    interrupted = Thread.currentThread().isInterrupted();
                }
                if (commandToRun.isFinished()) {
                    commandToRun.end();
                } else {
                    commandToRun.interrupted();
                }
            } catch (InterruptedException e) {
                commandToRun.interrupted();
            }
            this.commandToRun = null;
            return null;
        });
    }
}
