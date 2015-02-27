package org.usfirst.frc.team3414.teleop;

import edu.wpi.first.wpilibj.Joystick;

public class Logitech3DProJoystick extends Thread implements IJoystick
{
	Joystick joy;
	
	protected Logitech3DProJoystick(int channel)
	{
		joy = new Joystick(channel);
	}

	public double getAxis(JoystickAxis axis)
	{
		return joy.getRawAxis(axis.getValue());
	}

	public double getDirectionDegrees()
	{
		return joy.getDirectionDegrees();
	}

	public double getMagnitude()
	{
		return joy.getMagnitude();
	}

	public double getTwist()
	{
		return joy.getTwist();
	}

	public boolean getButton(JoystickButtons button)
	{
		return joy.getRawButton(button.getValue());
	}

}
