package org.usfirst.frc.team3414.teleop;

public class ButtonEventArgs
{
	public long buttonEventID;
	JoystickButtons buttonPressed;
	boolean override;
	ButtonStates buttonState;

	public ButtonEventArgs(long buttonEventID, JoystickButtons button, ButtonStates buttonState)
	{
		super();
		this.buttonEventID = buttonEventID;
		this.buttonPressed = button;
		this.buttonState = buttonState;
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
	
	public ButtonStates getButtonState()
	{
		return buttonState;
	}
}
