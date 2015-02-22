package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.actuators.*;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;
import org.usfirst.frc.team3414.sensors.VirtualClock;

public class TeleopControl
{
	private SensorConfig sensors;
	private ActuatorConfig actuators;
	private IDriveTrain driveTrain;
	private ILiftAssist lifter;
	private IServo servo;
	private MyJoystick joy;

	private boolean restrictButtonOne = false;
	private boolean restrictButtonTwo = false;
	private boolean restrictButtonThree = false;
	private boolean restrictButtonFour = false;
	private boolean restrictButtonFive = false;
	private boolean restrictButtonSix = false;
	private boolean restrictButtonSeven = false;
	private boolean restrictButtonEight = false;
	private boolean restrictButtonNine = false;
	private boolean restrictButtonTen = false;
	private boolean restrictButtonEleven = false;
	private boolean restrictButtonTwelve = false;

	private final int DRIVE_JOYSTICK_PORT = 1;
	private boolean isRegularMove = true;
	private boolean isTestMode = false;

	public TeleopControl(SensorConfig _sensors, ActuatorConfig _actuators)
	{
		this.sensors = _sensors;
		this.actuators = _actuators;
		/*
		 * this.sensors.getClock().addTimeListener(new ITimeListener() {
		 * 
		 * @Override public void timeEvent(TimeEventArgs timeEvent) { // do some
		 * code here
		 * 
		 * } }, 1000);
		 */
		lifter = actuators.getForklift();
		lifter.goToBottomLimit();
		servo = actuators.getServo();
		driveTrain = actuators.getDriveTrain();
		joy = new MyJoystick(DRIVE_JOYSTICK_PORT);
	}

	public void runTeleop()
	{

		if (isRegularMove)
		{
			driveTrain.move(joy.getMagnitude(), joy.getDirectionDegrees(), joy.getTwist());
		}
		if (isTestMode)
		{
			servo.dashboardSetServo();
		} else
		{
			this.forkliftOps();
		}

		lifter.toDisplay();
		driveTrain.toDisplay();
		Display.getInstance().setJoyData(joy.getMagnitude(), joy.getDirectionDegrees(), joy.getTwist());
	}

	private void forkliftOps()
	{
		// BUTTON SEVEN
		if (joy.getButtonSeven() && !restrictButtonSeven)
		{
			restrictButtonSeven = true;
			lifter.previousToteLength();
		}
		if (!joy.getButtonSeven() && restrictButtonSeven)
		{
			restrictButtonSeven = false;
		}

		// BUTTON EIGHT
		if (joy.getButtonEight() && !restrictButtonEight)
		{
			restrictButtonEight = true;
			lifter.nextToteLength();
		}
		if (!joy.getButtonEight() && restrictButtonEight)
		{
			restrictButtonEight = false;
		}

		// BUTTON NINE
		if (joy.getButtonNine() && !restrictButtonNine)
		{
			restrictButtonNine = true;
			lifter.previousBinLength();
		}
		if (!joy.getButtonNine() && restrictButtonNine)
		{
			restrictButtonNine = false;
		}

		// BUTTON TEN
		if (joy.getButtonTen() && !restrictButtonTen)
		{
			restrictButtonTen = true;
			lifter.nextBinLength();
		}
		if (!joy.getButtonTen() && restrictButtonTen)
		{
			restrictButtonTen = false;
		}

		// BUTTON ElEVEN
		if (joy.getButtonEleven() && !restrictButtonEleven)
		{
			restrictButtonEleven = true;
			lifter.goToGround();
		}
		if (!joy.getButtonEleven() && restrictButtonEleven)
		{
			restrictButtonEleven = false;
		}

		// BUTTON TWELVE
		if (joy.getButtonTwelve() && !restrictButtonTwelve)
		{
			restrictButtonTwelve = true;
			lifter.goToBottomLimit();
		}
		if (!joy.getButtonTwelve() && restrictButtonTwelve)
		{
			restrictButtonTwelve = false;
		}
	}

	public void setRegularMove(boolean _isRegularMove)
	{
		this.isRegularMove = _isRegularMove;
	}

	public void isTestingRobot(boolean _isTestMode)
	{
		this.isTestMode = _isTestMode;

	}
}