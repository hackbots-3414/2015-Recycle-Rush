package org.usfirst.frc.team3414.teleop;

import java.util.Map;

public interface IGamepad extends IHaveButtons
{
	Map<DPadDirection, Boolean> getDPad();
	double getAxis(JoystickSide side, JoystickAxis axis);
}
