package org.usfirst.frc.team3414.actuators;



public interface ILiftAssist
{
	public void goToGround();

	public void goToTopLimit();
	
	public void goToBottomLimit();

	public void stop();

	public void previousToteLength();

	public void nextToteLength();

	public void previousBinLength();

	public void nextBinLength();

}
