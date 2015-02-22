package org.usfirst.frc.team3414.actuators;

/**
 * Implemented by Servo.java
 * Used by Forklift.java
 *
 */
public interface IServo
{
	/**
	 * Servo Set to 1.0
	 */
	public void engage();

	/**
	 * Servo Set to 0.7
	 */
	public void disengage();

	public boolean getEngaged();
	
	/**
	 * For Testing Purposes ONLY
	 */
	public void dashboardSetServo();
}