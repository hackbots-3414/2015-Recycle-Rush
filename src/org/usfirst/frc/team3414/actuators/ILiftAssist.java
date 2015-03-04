package org.usfirst.frc.team3414.actuators;

/**
 * 
 * implemented by Forklift.java
 * 
 */

public interface ILiftAssist
{
	// PUBLIC FUNCTIONS
	public void upLift();

	public void downLift();

	public void stopLift();

	public void goToTopLimit();

	public void goToBottomLimit();

	public void previousToteLength();

	public void nextToteLength();

	public void previousBinLength();

	public void nextBinLength();
	
	// PRIVATE OPERATIONS

	// PRIVATE BASELINE FUNCTIONS

	// OTHER
	public void toDisplay();

	public void calibrate();

	public void setEStopAllAction(boolean override);

}
