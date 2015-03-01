package org.usfirst.frc.team3414.teleop;

public interface IButtonEventHandler
{
	public long addButtonListener(IButtonListener listener, JoystickButtons button, boolean repeat);
	
	public void removeListener(long buttonEventID);

	public long addButtonListener(IButtonListener listener, JoystickButtons button);

	long addButtonListener(IButtonListener listener, JoystickButtons button, boolean repeat, ButtonStates whenToFire);
	
	void clearQueue();
}
