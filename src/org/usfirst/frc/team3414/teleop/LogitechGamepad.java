package org.usfirst.frc.team3414.teleop;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamepad implements IGamepad
{
	private Joystick gamepad;
	private Map<DPadDirection, Boolean> dPadOutput = new HashMap<>();

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

		// update with correct values for the DPad
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

		if (side == JoystickSide.LEFT)
		{
			switch (axis)
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
		} else if (side == JoystickSide.RIGHT)
		{
			switch (axis)
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
	
	private double calculateMagnitude(int a, int b)
	{
		return Math.sqrt((Math.pow(gamepad.getRawAxis(a), 2) + (Math.pow(gamepad.getRawAxis(b), 2))));
	}

	public double getMagnitude(JoystickSide side)
	{
		if (side == JoystickSide.RIGHT)
			return calculateMagnitude(2,3);
		else
			return calculateMagnitude(0,1);
	}

	public double getDirection(JoystickSide side)
	{
		int set = 0;
		
		if (side == JoystickSide.RIGHT)
		{
			set = 2;
		}
		double x = gamepad.getRawAxis(set);
		double y = gamepad.getRawAxis(set+1);
		return Math.toDegrees(Math.atan2(x, -y));
//		if (x == 0)
//		{
//			if (y > 0) {
//				return 90; 
//			} else if (y < 0) {
//				return 180;
//			} else {
//				return 0;
//			}
//			
//		} else if (y == 0) {
//			if (x > 0) {
//				return 90; 
//			} else if (x < 0) {
//				return -90;
//			} else {
//				return 0;
//			}
//		}else 
//		{
//			return (Math.toDegrees(Math.atan(y / x)));
//		}
	}

	public double getTwist(JoystickSide side)
	{
		if (side == JoystickSide.RIGHT)
			return gamepad.getRawAxis(2);
		else
			return gamepad.getRawAxis(0);
	}

}
