package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.IEncoder;

/**
 * implemented by EncodedMotor.java
 * used by Forklift.java
 *
 */
public interface IEncodedMotor extends IMotor, IEncoder
{
	static final int DISTANCE_PER_PULSE = 1;
}
