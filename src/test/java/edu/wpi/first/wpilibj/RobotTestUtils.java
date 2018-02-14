/*----------------------------------------------------------------------------*/
/* Copyright (c) 2016-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj;

import static org.powermock.api.support.membermodification.MemberMatcher.constructor;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.team281.robot.logger.DataLogger;
import frc.team281.robot.logger.DataLoggerFactory;

/**
 * Utility class for configuring and running unit tests. Its necessary to do a
 * lot of hacks to run the wpilib scheduler outside of a robot.
 * 
 * use setupForTesting() to do this work.
 * 
 * Then, use the methods on this class to schedule and run commands within your
 * tests.
 */
public final class RobotTestUtils {

    public static final long DELAY_MILLIS = 20;
    private static DataLogger dataLogger;

    public static void setupForTesting() {

        setupWpiLib();
        DataLoggerFactory.configureForTesting();
        RobotTestUtils.dataLogger = DataLoggerFactory.getLoggerFactory().createDataLogger("Test Utils");
    }

    /**
     * Sets up the base system WPILib so that it does not rely on hardware.
     */
    protected static void setupWpiLib() {

        RobotState.SetImplementation(new MockRobotStateInterface());
        Timer.SetImplementation(new Timer.StaticInterface() {

            @Override
            public double getFPGATimestamp() {
                return System.currentTimeMillis() / 1000.0;
            }

            @Override
            public double getMatchTime() {
                return 0;
            }

            @Override
            public void delay(double seconds) {
                try {
                    Thread.sleep((long) (seconds * 1e3));
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Thread was interrupted", ex);
                }
            }

            @Override
            public Timer.Interface newTimer() {
                return new Timer.Interface() {
                    private final Stopwatch m_stopwatch = Stopwatch.createUnstarted();

                    @Override
                    public double get() {
                        return m_stopwatch.elapsed(TimeUnit.SECONDS);
                    }

                    @Override
                    public void reset() {
                        m_stopwatch.reset();
                    }

                    @Override
                    public void start() {
                        m_stopwatch.start();
                    }

                    @Override
                    public void stop() {
                        m_stopwatch.stop();
                    }

                    @Override
                    public boolean hasPeriodPassed(double period) {
                        if (get() > period) {
                            // Advance the start time by the period.
                            // Don't set it to the current time... we want to avoid drift.
                            m_stopwatch.reset().start();
                            return true;
                        }
                        return false;
                    }
                };
            }
        });

        suppress(constructor(SendableBase.class, Boolean.class));
        suppress(method(HLUsageReporting.class, "reportScheduler"));
    }

    public static void delayMillis(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    public static void schedule(Command command) {
        Scheduler.getInstance().add(command);
    }

    public static void runForSeconds(double seconds) {
        RobotTestUtils.dataLogger.log("Running for Seconds", seconds);
        long currentTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + (long) (seconds * 1000);
        while (currentTime < endTime) {
            RobotTestUtils.dataLogger.log("Clock", "Tick");
            Scheduler.getInstance().run();
            delayMillis(DELAY_MILLIS);
            currentTime = System.currentTimeMillis();
        }
    }

}
