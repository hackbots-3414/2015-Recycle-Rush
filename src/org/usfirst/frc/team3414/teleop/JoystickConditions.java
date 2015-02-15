package org.usfirst.frc.team3414.teleop;

import edu.wpi.first.wpilibj.Joystick;

public class JoystickConditions implements IAxis, IButtons
{
	Joystick joy;

	public JoystickConditions()
	{
		joy = new Joystick(1);
	}

	public double getVerticalAxis()
	{
		return joy.getRawAxis(1);
	}

	public double getHorizontalAxis()
	{
		return joy.getRawAxis(2);
	}

	public double getRotationalAxis()
	{
		return joy.getRawAxis(3);
	}

	public double getGamepadVerticalAxisOne()
	{
		return joy.getRawAxis(1);
	}

	public double getGamepadHorizontalAxisOne()
	{
		return joy.getRawAxis(2);
	}

	public double getGamepadVerticalAxisTwo()
	{
		return joy.getRawAxis(3);
	}

	public double getGamepadHorizontalAxisTwo()
	{
		return joy.getRawAxis(4);
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

	public boolean getButtonOne()
	{
		return joy.getRawButton(1);
	}

	public boolean getButtonTwo()
	{
		return joy.getRawButton(2);
	}

	public boolean getButtonThree()
	{
		return joy.getRawButton(3);
	}

	public boolean getButtonFour()
	{
		return joy.getRawButton(4);
	}

	public boolean getButtonFive()
	{
		return joy.getRawButton(5);
	}

	public boolean getButtonSix()
	{
		return joy.getRawButton(6);
	}

	public boolean getButtonSeven()
	{
		return joy.getRawButton(7);
	}

	public boolean getButtonEight()
	{
		return joy.getRawButton(8);
	}

	public boolean getButtonNine()
	{
		return joy.getRawButton(9);
	}

	public boolean getButtonTen()
	{
		return joy.getRawButton(10);
	}

	public boolean getButtonEleven()
	{
		return joy.getRawButton(11);
	}

	public boolean getButtonTwelve()
	{
		return joy.getRawButton(12);
	}

}
