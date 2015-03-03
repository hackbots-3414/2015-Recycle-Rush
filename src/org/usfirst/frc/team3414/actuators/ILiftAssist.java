package org.usfirst.frc.team3414.actuators;


/**
 * 
 * implemented by Forklift.java
 * 
 */

public interface ILiftAssist
{	
	public void goToTopLimit();
	
	public void goToBottomLimit();

	public void previousToteLength();

	public void nextToteLength();

	public void previousBinLength();

	public void nextBinLength();
	
	public void toDisplay();
	
	public void calibrate();
	
	public void stop();
	
	public void stopAction();

	public void up();
	
	public void unlockLift();
	
	public void down();
	
	public void waitServo();
}
