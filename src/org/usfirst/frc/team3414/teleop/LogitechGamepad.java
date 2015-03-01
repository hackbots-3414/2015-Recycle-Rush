package org.usfirst.frc.team3414.teleop;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamepad implements IGamepad
{
	Joystick gamepad;
	
	public LogitechGamepad(int gamepadPort)
	{
		this.gamepad = new Joystick(gamepadPort);
	}

	@Override
	public boolean getButton(JoystickButtons button)
	{
		return gamepad.getRawButton(button.getValue());
	}

	@Override
	public Map<DPadDirection, Boolean> getDPad()
	{
		Map<DPadDirection, Boolean> dPadOutput = new HashMap<>();
		
		//update with correct values for the DPad
		dPadOutput.put(DPadDirection.UP, (gamepad.getRawAxis(0) > 0.7));
		dPadOutput.put(DPadDirection.DOWN, (gamepad.getRawAxis(0) < -0.7));
		
		dPadOutput.put(DPadDirection.LEFT, (gamepad.getRawAxis(1) < -0.7));
		dPadOutput.put(DPadDirection.RIGHT, (gamepad.getRawAxis(1) > 0.7));
		
		return dPadOutput;
	}

	@Override
	public double getAxis(JoystickSide side, JoystickAxis axis)
	{
		double axisValue = 0.0;
		
		if(side == JoystickSide.LEFT)
		{
			switch(axis)
			{
			case HORIZONTAL_AXIS:
				axisValue = gamepad.getRawAxis(0);
				break;
			case THROTTLE_AXIS:
				break;
			case TWIST_AXIS:
				break;
			case VERTICAL_AXIS:
				axisValue = gamepad.getRawAxis(1);
				break;
			default:
				break;
			}
		} else if(side == JoystickSide.RIGHT)
		{
			switch(axis)
			{
			case HORIZONTAL_AXIS:
				axisValue = gamepad.getRawAxis(2);
				break;
			case THROTTLE_AXIS:
				break;
			case TWIST_AXIS:
				break;
			case VERTICAL_AXIS:
				axisValue = gamepad.getRawAxis(3);
				break;
			default:
				break;
			}
		}
		
		return axisValue;
	}

}
