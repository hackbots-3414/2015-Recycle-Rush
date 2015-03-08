package org.usfirst.frc.team3414.teleop;

import java.util.Map;

public interface IGamepad extends IHaveButtons
{
	Map<DPadDirection, Boolean> getDPad();
	double getAxis(JoystickSide side, JoystickAxis axis);
	
	public double getMagnitude (JoystickSide side);
	public double getDirection (JoystickSide side);
	public double getTwist (JoystickSide side);
}
