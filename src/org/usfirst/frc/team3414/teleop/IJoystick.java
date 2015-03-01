package org.usfirst.frc.team3414.teleop;

public interface IJoystick extends IHaveButtons
{
	public double getDirectionDegrees();
	public double getMagnitude();
	public double getTwist();
	public double getAxis(JoystickAxis axis);
}
