package org.usfirst.frc.team3414.teleop;

public interface IJoystick
{
	public double getAxis(JoystickAxis axis);
	public double getDirectionDegrees();
	public double getMagnitude();
	public double getTwist();
	public boolean getButton(JoystickButtons button);
}
