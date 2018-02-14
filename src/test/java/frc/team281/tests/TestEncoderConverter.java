package frc.team281.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import frc.team281.robot.subsystems.EncoderInchesConverter;

public class TestEncoderConverter {

    @Test
    public void testEncoderConverter() {

        EncoderInchesConverter eic = new EncoderInchesConverter(10.0);
        assertEquals(100.0, eic.toCounts(10.0), 0.001);
        assertEquals(1.0, eic.toInches(10), 0.001);

    }

    @Test
    public void testEncoderConverterCals() {

        double gearRatio = 14.0 / 52.0 * 14.0 / 52.0;
        EncoderInchesConverter eic = new EncoderInchesConverter(80, 6.0, gearRatio);
        assertEquals(3.0, eic.toCounts(10.0), 0.001);
        assertEquals(32.5058, eic.toInches(10), 0.001);

    }
}
