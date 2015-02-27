package org.usfirst.frc.team3414.actuators;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JToggleButton.ToggleButtonModel;

import org.usfirst.frc.team3414.sensors.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift implements ILiftAssist
{
	private static final int UNLOCK_TIME = 150;
	private static final double ALLOWANCE = 5.0;
	private static final double LIFTER_UP_SPEED = 1.0;
	private static final double LIFTER_DOWN_SPEED = 1.0;
	private static final double LIFTER_TOP_LIMIT = 20000;
	private static final double LIFTER_BOTTOM_LIMIT = 200;

	private static final int TOTE_ENCODER_COUNT = 1500;
	private static final int BIN_ENCODER_COUNT = 700;

	private IEncodedMotor encodedMotor;
	private IServo latch;
	private ILimitSwitch topSwitch;
	private ILimitSwitch botSwitch;
	private boolean isCalibrated = false;
	private IClock clock;
	private int goToPosition = 0;
	private ExecutorService executor;

	// private double gravityTestMotorSpeed = 0;

	protected Forklift(IEncodedMotor motor, ILimitSwitch topSwitch, ILimitSwitch bottomSwitch, IServo servo, IClock clock)
	{
		encodedMotor = motor;
		this.topSwitch = topSwitch;
		this.botSwitch = bottomSwitch;
		latch = servo;
		latch.disengage();
		this.clock = clock;
		this.executor = Executors.newFixedThreadPool(1);
	}

	@Override
	public void goToGround()
	{
		goToPosition = 1;
	}

	public void stopLift()
	{
		encodedMotor.stop();
		latch.engage();
	}

	@Override
	public void goToBottomLimit()
	{
		unlockLift();
		if (!isAtBottom())
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		}
		while (!isAtBottom())
		{

		}

		encodedMotor.stop();
		lockLift();
	}

	private boolean isAtBottom()
	{
		return isCalibrated ? botSwitch.isHit() || encodedMotor.getPosition() <= LIFTER_BOTTOM_LIMIT : botSwitch.isHit();
	}

	private void lockLift()
	{
		latch.engage();
	}

	private void unlockLift()
	{
		latch.disengage();
		encodedMotor.up(LIFTER_UP_SPEED);
		long time = clock.getTimeInMillis();
		while (clock.getTimeInMillis() < time + UNLOCK_TIME)
		{
			// Wait for unlock
		}
		encodedMotor.stop();
	}

	@Override
	public void goToTopLimit()
	{
		unlockLift();
		if (!isAtTop())
		{
			encodedMotor.up(LIFTER_UP_SPEED);
		}
		while (!isAtTop())
		{

		}

		encodedMotor.stop();
		lockLift();

	}

	private boolean isAtTop()
	{
		return isCalibrated ? topSwitch.isHit() || encodedMotor.getPosition() >= LIFTER_TOP_LIMIT : topSwitch.isHit();
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousToteLength()
	{
		previousLength(TOTE_ENCODER_COUNT);
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextToteLength()
	{
		nextLength(TOTE_ENCODER_COUNT);
	}

	private void nextLength(int encoderCount)
	{
		executor.submit(() -> {
			if (!isAtTop())
			{
				int previousDistance = encodedMotor.getPosition();
				unlockLift();
				if (!isAtTop() && encodedMotor.getPosition() < (previousDistance + encoderCount))
				{
					encodedMotor.up(LIFTER_UP_SPEED);
				}
				while (!isAtTop() && encodedMotor.getPosition() < (previousDistance + encoderCount))
				{
				}
			}
			encodedMotor.stop();
			lockLift();
		});

	}

	private void previousLength(int encoderCount)
	{
		executor.submit(() -> {
			if (!isAtBottom())
			{
				int previousDistance = encodedMotor.getPosition();
				unlockLift();
				if (!isAtBottom() && encodedMotor.getPosition() > (previousDistance - encoderCount))
				{
					encodedMotor.down(LIFTER_DOWN_SPEED);
				}
				while (!isAtBottom() && encodedMotor.getPosition() > (previousDistance - encoderCount))
				{
				}
			}
			encodedMotor.stop();
			lockLift();
		});
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousBinLength()
	{
		previousLength(BIN_ENCODER_COUNT);
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextBinLength()
	{
		nextLength(BIN_ENCODER_COUNT);
	}

	@Override
	public void toDisplay()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void calibrate()
	{
		executor.submit(() -> {
			goToBottomLimit();
			encodedMotor.reset();
			SmartDashboard.putNumber("Bottom", encodedMotor.getPosition());
			// goToTopLimit();
			// SmartDashboard.putNumber("Top", encodedMotor.getPosition());
				isCalibrated = true;
			});

	}

}