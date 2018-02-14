package frc.team281.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.RobotTestUtils;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Base class for all robot unit tests. Make sure to extend this class for yoru
 * tests, so that everything is all set up to work correctly.
 * 
 * @author dcowden
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Scheduler.class, HLUsageReporting.class, SendableBase.class })
@PowerMockIgnore("org.jacoco.agent.rt.*")
public class BaseTest {

    @Test
    public void testFrameworkSetup() {

    }

    @Before
    public void setupWpiLib() {
        RobotTestUtils.setupForTesting();
    }

}
