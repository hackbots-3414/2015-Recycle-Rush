package org.usfirst.frc.team3414.teleop;

public class ButtonEventArgs
{
	public long buttonEventID;
	JoystickButtons buttonPressed;
	boolean override;

	public ButtonEventArgs(long buttonEventID, JoystickButtons button)
	{
		super();
		this.buttonEventID = buttonEventID;
		this.buttonPressed = button;
	}
	
	public long getButtonEventID()
	{
		return buttonEventID;
	}
	
	public JoystickButtons getButtonPressed()
	{
		return buttonPressed;
	}
	
	public boolean isOverriden()
	{
		return override;
	}
}
