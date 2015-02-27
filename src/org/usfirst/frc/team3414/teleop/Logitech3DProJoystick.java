package org.usfirst.frc.team3414.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logitech3DProJoystick implements IJoystick
{
	Joystick joy;

	public Logitech3DProJoystick(int channel)
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
//		SmartDashboard.putNumber("Joystick Axis", getMagnitude());
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
