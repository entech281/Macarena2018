package frc.team281.tests;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import frc.team281.robot.OperatorInterface;;
public class TestOI {
    
    @Test
    public void testJoystickSoftness() {
        assertTrue(OperatorInterface.adjustJoystickSoftness(1.5, 1)==1);
        assertTrue(OperatorInterface.adjustJoystickSoftness(1.35, -1)==-1);
        assertTrue(OperatorInterface.adjustJoystickSoftness(1.573, 0)==0);
    }
}
