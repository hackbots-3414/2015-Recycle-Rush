package org.usfirst.frc.team3414.teleop;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.autonomous.AutonomousConfig;
import org.usfirst.frc.team3414.autonomous.IDriverAssist;
import org.usfirst.frc.team3414.sensors.IClock;
import org.usfirst.frc.team3414.sensors.ITimeListener;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;
import org.usfirst.frc.team3414.sensors.TimeEventArgs;

public class TeleopControl
{
	private IJoystick joystick;
	private IDriveTrain driveTrain;
	private ILiftAssist lifter;
	private IClock clock;

	private IDriverAssist driverAssist;

	private final int REFRESH_RATE_MS = 200;

	private final JoystickButtons UP_TOTE = JoystickButtons.EIGHT;
	private final JoystickButtons DOWN_TOTE = JoystickButtons.SEVEN;
	private final JoystickButtons UP_BIN = JoystickButtons.TEN;
	private final JoystickButtons DOWN_BIN = JoystickButtons.NINE;
	private final JoystickButtons GO_TO_TOP = JoystickButtons.TWELVE;
	private final JoystickButtons GO_TO_BOTTOM = JoystickButtons.ELEVEN;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_WIDE = JoystickButtons.FIVE;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_THIN = JoystickButtons.THREE;
	private final JoystickButtons TRIGGER_SLOW_JOYSTICK = JoystickButtons.ONE;
	
	final int JOYSTICK_PORT = 1;

	List<Long> eventID = new ArrayList<>();

	public TeleopControl()
	{
		SensorConfig sensors = SensorConfig.getInstance();
		ActuatorConfig actuators = ActuatorConfig.getInstance();

		this.clock = sensors.getClock();
		this.lifter = actuators.getForklift();
		this.driveTrain = actuators.getDriveTrain();
		this.driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		this.joystick = new Logitech3DProJoystick(JOYSTICK_PORT);

		lifter.goToBottomLimit();
	}

	private double getJoystickLimitingValue(double input)
	{
		double returnValue;
		
		if(input > 0)
		{
			returnValue = Math.pow(input, 2);
		} else if (input < 0)
		{
			returnValue = -Math.pow(input, 2);
		} else
		{
			returnValue = 0.0;
		}
		
		return returnValue;
	}
	
	public void enable()
	{
		eventID.add(clock.addTimeListener(new ITimeListener()
		{

			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if (joystick.getMagnitude() > 0.1 && joystick.getMagnitude() < -0.1)
				{
					if (joystick.getButton(TRIGGER_SLOW_JOYSTICK))
					{
						driveTrain.move(getJoystickLimitingValue(joystick.getMagnitude())*.2, joystick.getDirectionDegrees(), joystick.getTwist()*.2);
					} else
					{
						driveTrain.move(getJoystickLimitingValue(joystick.getMagnitude()), joystick.getDirectionDegrees(), joystick.getTwist());
					}
				}

			}

		}, REFRESH_RATE_MS, true));

		eventID.add(clock.addTimeListener(new ITimeListener()
		{
			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if (joystick.getButton(UP_TOTE))
				{
					lifter.nextToteLength();
				}

				if (joystick.getButton(DOWN_TOTE))
				{
					lifter.previousToteLength();
				}

				if (joystick.getButton(DOWN_BIN))
				{
					lifter.previousBinLength();
				}

				if (joystick.getButton(UP_BIN))
				{
					lifter.nextBinLength();
				}

				if (joystick.getButton(GO_TO_TOP))
				{
					lifter.goToTopLimit();
				}

				if (joystick.getButton(GO_TO_BOTTOM))
				{
					lifter.goToGround();
				}
			}

		}, REFRESH_RATE_MS, true));

		eventID.add(clock.addTimeListener(new ITimeListener()
		{
			@Override
			public void timeEvent(TimeEventArgs timeEvent)
			{
				if (joystick.getButton(STREIGHTEN_WITH_TOTE_WIDE))
				{
					driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE);
					driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE);
				}

				if (joystick.getButton(STREIGHTEN_WITH_TOTE_THIN))
				{
					driverAssist.binSweetSpot(SweetSpotMode.TOTE_THIN);
					driverAssist.correctRotation(SweetSpotMode.TOTE_THIN);
				}
			}

		}, REFRESH_RATE_MS, true));
	}

	public void disable()
	{
		for (int i = 0; i < eventID.size(); i++)
		{
			clock.removeListener(eventID.get(i));
			eventID.remove(i);
		}
	}
}
