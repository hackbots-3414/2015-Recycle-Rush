package org.usfirst.frc.team3414.actuators;


/**
 * 
 * implemented by Forklift.java
 * 
 */

public interface ILiftAssist
{
	public void start();
	
	public void goToGround();

	public void goToTopLimit();
	
	/**
	 * Goes to bottom Limit
	 */
	public void goToBottomLimit();

	public void stop();

	public void previousToteLength();

	public void nextToteLength();

	public void previousBinLength();

	public void nextBinLength();
	
	public void toDisplay();

}
