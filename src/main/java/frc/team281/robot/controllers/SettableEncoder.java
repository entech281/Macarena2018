package frc.team281.robot.controllers;


import edu.wpi.first.wpilibj.Encoder;

/**
 * This is an encoder that allows setting its value to a given position.
 * When that happens, we track the difference between 
 * the actual reading and the reset value, to give the appearance that we reset it.
 * @author dave
 *
 */
public class SettableEncoder {

	private Encoder encoder;
	private int offset = 0;
	
	public SettableEncoder ( Encoder original ) {
		this.encoder = original;
	}
	
	public Encoder getRawEncoder() {
		return encoder;
	}
	public void reset() {
		encoder.reset();
		offset=0;
	}
	public void set(int counts) {
		int currentPos = encoder.get();
		offset = (counts - currentPos);
	}
	public int get() {
		return encoder.get() + offset;
	}
}
