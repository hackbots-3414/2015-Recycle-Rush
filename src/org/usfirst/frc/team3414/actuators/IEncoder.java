package org.usfirst.frc.team3414.actuators;

public interface IEncoder {
	
	public double getEncoderVelocity();
	
	public int getEncoderPosition();
	
	public void resetEncoder();

}
