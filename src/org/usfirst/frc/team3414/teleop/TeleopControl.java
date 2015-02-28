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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TeleopControl
{
	private IJoystick joystick;
	private IDriveTrain driveTrain;
	private ILiftAssist lifter;
	private IClock clock;

	private IDriverAssist driverAssist;

	private final int REFRESH_RATE_MS = 85;

	private final JoystickButtons UP_TOTE = JoystickButtons.EIGHT;
	private final JoystickButtons DOWN_TOTE = JoystickButtons.SEVEN;
	private final JoystickButtons UP_BIN = JoystickButtons.TEN;
	private final JoystickButtons DOWN_BIN = JoystickButtons.NINE;
	private final JoystickButtons GO_TO_TOP = JoystickButtons.TWELVE;
	private final JoystickButtons GO_TO_BOTTOM = JoystickButtons.ELEVEN;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_WIDE = JoystickButtons.FIVE;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_THIN = JoystickButtons.THREE;
	private final JoystickButtons TRIGGER_SLOW_JOYSTICK = JoystickButtons.ONE;
	private final JoystickButtons OVERRIDE_BUTTON = JoystickButtons.TWO;

	final int JOYSTICK_PORT = 1;

	List<Long> timeEventID = new ArrayList<>();
	List<Long> buttonEventID = new ArrayList<>();
	private ButtonEventHandler joystickEventHandler;

	public TeleopControl()
	{
		SensorConfig sensors = SensorConfig.getInstance();
		ActuatorConfig actuators = ActuatorConfig.getInstance();

		this.clock = sensors.getClock();
		this.lifter = actuators.getForklift();
		this.driveTrain = actuators.getDriveTrain();
		this.driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		this.joystick = new Logitech3DProJoystick(JOYSTICK_PORT);
		this.joystickEventHandler = new ButtonEventHandler(joystick);
	}

	private double getJoystickLimitingValue(double input)
	{
		double returnValue;

		if (input > 0)
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

	private void displayStuff()
	{
		Display.getInstance().setJoyData(joystick.getMagnitude(), joystick.getDirectionDegrees(), joystick.getTwist());
		lifter.toDisplay();
		driveTrain.toDisplay();
		Display.getInstance().putDiagnosticsData();
	}

	public void enable()
	{
		lifter.calibrate();
		displayStuff();
		timeEventID.add(clock.addTimeListener((event) -> {

			if (joystick.getButton(TRIGGER_SLOW_JOYSTICK))
			{
				driveTrain.move(getJoystickLimitingValue(joystick.getMagnitude()), joystick.getDirectionDegrees(),
						getJoystickLimitingValue(joystick.getTwist()));
			} else
			{
				driveTrain.move(getJoystickLimitingValue(joystick.getMagnitude()) * .4, joystick.getDirectionDegrees(),
						getJoystickLimitingValue(joystick.getTwist()) * .4);

			}
		}, driveTrain.getSafetyTimeout() / 4, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.nextToteLength();
		}, UP_TOTE, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.previousToteLength();
		}, DOWN_TOTE, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.previousBinLength();
		}, DOWN_BIN, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.nextBinLength();
		}, UP_BIN, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.previousBinLength();
		}, DOWN_BIN, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.goToTopLimit();
		}, GO_TO_TOP, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			lifter.goToBottomLimit();
		}, GO_TO_BOTTOM, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE);
			driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE);
		}, STREIGHTEN_WITH_TOTE_WIDE, true));

		buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
			driverAssist.binSweetSpot(SweetSpotMode.TOTE_THIN);
			driverAssist.correctRotation(SweetSpotMode.TOTE_THIN);
		}, STREIGHTEN_WITH_TOTE_THIN, true));

		timeEventID.add(clock.addTimeListener((event) -> {
			SmartDashboard.putBoolean("A", false);
			if (joystick.getButton(OVERRIDE_BUTTON))
			{
				SmartDashboard.putBoolean("A", true);
				joystickEventHandler.clearQueue();
				lifter.forkOverride();
				
				//lifter.stop();
			}
		}, 10, true));

	}

	public void disable()
	{
		for (int i = 0; i < timeEventID.size(); i++)
		{
			clock.removeListener(timeEventID.get(i));
			timeEventID.remove(i);
		}

		for (int i = 0; i < buttonEventID.size(); i++)
		{
			joystickEventHandler.removeListener(buttonEventID.get(i));
			buttonEventID.remove(i);
		}
	}
}
