package org.usfirst.frc.team3414.teleop;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.autonomous.AutonomousConfig;
import org.usfirst.frc.team3414.autonomous.IDriverAssist;
import org.usfirst.frc.team3414.sensors.IClock;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;

public class TeleopControl
{
	private IJoystick joystick;
	private IGamepad gamepad;
	private IDriveTrain driveTrain;
	private ILiftAssist lifter;
	private IClock clock;

	private IDriverAssist driverAssist;

	private final int OVERRIDE_REFRESH_RATE_MS = 10;

	private final JoystickButtons UP_TOTE = JoystickButtons.FIVE;
	private final JoystickButtons DOWN_TOTE = JoystickButtons.SEVEN;
	private final JoystickButtons UP_BIN = JoystickButtons.SIX;
	private final JoystickButtons DOWN_BIN = JoystickButtons.EIGHT;
	private final JoystickButtons GO_TO_TOP = JoystickButtons.TWELVE;
	private final JoystickButtons GO_TO_BOTTOM = JoystickButtons.ELEVEN;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_WIDE = JoystickButtons.FIVE;
	private final JoystickButtons STREIGHTEN_WITH_TOTE_THIN = JoystickButtons.THREE;
	private final JoystickButtons TRIGGER_SLOW_JOYSTICK = JoystickButtons.ONE;
	private final JoystickButtons OVERRIDE_BUTTON = JoystickButtons.TEN;
	private final JoystickButtons DO_LIFTER_COMMANDS = JoystickButtons.NINE;

	private final JoystickButtons UP = JoystickButtons.FOUR;
	private final JoystickButtons DOWN = JoystickButtons.TWO;

	final int JOYSTICK_PORT = 1;
	final int GAMEPAD_PORT = 2;

	List<Long> timeEventID = new ArrayList<>();
	List<Long> buttonEventID = new ArrayList<>();

	private ButtonEventHandler joystickEventHandler;
	private ButtonEventHandler gamepadEventHandler;

	public TeleopControl()
	{
		SensorConfig sensors = SensorConfig.getInstance();
		ActuatorConfig actuators = ActuatorConfig.getInstance();

		this.clock = sensors.getClock();
		this.lifter = actuators.getForklift();
		this.driveTrain = actuators.getDriveTrain();
		this.driverAssist = AutonomousConfig.getInstance().getDriveAssist();
		this.joystick = new Logitech3DProJoystick(JOYSTICK_PORT);
		this.gamepad = new LogitechGamepad(GAMEPAD_PORT);

		this.joystickEventHandler = new ButtonEventHandler(joystick);
		this.gamepadEventHandler = new ButtonEventHandler(gamepad);
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

		timeEventID.add(clock.addTimeListener((event) -> {
			if (gamepad.getButton(DO_LIFTER_COMMANDS))
			{
				joystickEventHandler.setAbleToDoCommands(true);
			} else
			{
				joystickEventHandler.setAbleToDoCommands(false);
				if (gamepad.getButton(UP))
				{
					lifter.upLift();

				} else if (gamepad.getButton(DOWN))
				{
					lifter.downLift();

				} else
				{
					lifter.stopLift();
				}
			}
		}, OVERRIDE_REFRESH_RATE_MS, true));

		timeEventID.add(clock.addTimeListener((event) -> {
			displayStuff();
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

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.nextToteLength();
		}, UP_TOTE, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.previousToteLength();
		}, DOWN_TOTE, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.previousBinLength();
		}, DOWN_BIN, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.nextBinLength();

		}, UP_BIN, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.previousBinLength();
		}, DOWN_BIN, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.goToTopLimit();
		}, GO_TO_TOP, true));

		buttonEventID.add(gamepadEventHandler.addButtonListener((event) -> {
			lifter.goToBottomLimit();
		}, GO_TO_BOTTOM, true));

		// buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
		// driverAssist.binSweetSpot(SweetSpotMode.TOTE_WIDE);
		// driverAssist.correctRotation(SweetSpotMode.TOTE_WIDE);
		// }, STREIGHTEN_WITH_TOTE_WIDE, true));
		//
		// buttonEventID.add(joystickEventHandler.addButtonListener((event) -> {
		// driverAssist.binSweetSpot(SweetSpotMode.TOTE_THIN);
		// driverAssist.correctRotation(SweetSpotMode.TOTE_THIN);
		// }, STREIGHTEN_WITH_TOTE_THIN, true));

		// buttonEventID.add(joystickEventHandler.addButtonListener((event) ->
		// {
		// lifter.up();
		// }, UP, true, ButtonStates.PRESSED));
		//
		// buttonEventID.add(joystickEventHandler.addButtonListener((event) ->
		// {
		// lifter.stop();
		// }, UP, true, ButtonStates.RELEASED));
		//
		// buttonEventID.add(joystickEventHandler.addButtonListener((event) ->
		// {
		// lifter.down();
		// }, DOWN, true, ButtonStates.PRESSED));
		//
		// buttonEventID.add(joystickEventHandler.addButtonListener((event) ->
		// {
		// lifter.stop();
		// }, DOWN, true, ButtonStates.RELEASED));

		timeEventID.add(clock.addTimeListener((event) -> {
			if (gamepad.getButton(OVERRIDE_BUTTON))
			{
				gamepadEventHandler.clearQueue();
				lifter.setEStopAllAction(true);
			} else
			{
				lifter.setEStopAllAction(false);
			}
		}, OVERRIDE_REFRESH_RATE_MS, true));

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
