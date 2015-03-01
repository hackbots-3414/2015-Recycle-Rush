package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.*;
import org.usfirst.frc.team3414.teleop.Display;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift implements ILiftAssist
{
	private static final int UNLOCK_TIME = 500;
	private static final double LIFTER_UP_SPEED = 1.0;
	private static final double LIFTER_DOWN_SPEED = 1.0;
	private static final double CALIBRATE_SPEED = 0.2;
	private static final double LIFTER_TOP_LIMIT = 19500;
	private static final double LIFTER_BOTTOM_LIMIT = 200;

	private static final double TOTE_HEIGHT_IN = 12;
	private static final double BIN_HEIGHT_IN = 28.75;

	private IEncodedMotor encodedMotor;
	private IServo latch;
	private ILimitSwitch topSwitch;
	private ILimitSwitch botSwitch;
	private boolean isCalibrated = false;
	private IClock clock;
	
	private boolean forkOverride = false;

	protected Forklift(IEncodedMotor motor, ILimitSwitch topSwitch, ILimitSwitch bottomSwitch, IServo servo, IClock clock)
	{
		encodedMotor = motor;
		this.topSwitch = topSwitch;
		this.botSwitch = bottomSwitch;
		latch = servo;
		latch.disengage();
		this.clock = clock;
	}

	public void stop()
	{
		if(encodedMotor.getRate() >= 0)
		{
			stopLiftUp();
		} else
		{
			stopLiftDown();
		}
//		encodedMotor.stop();
//		lockLift();
	}

	private void stopLiftDown()
	{
		if (!forkOverride)
		{
			encodedMotor.stop();
			waitServo();
			lockLift();
		} else
		{
			encodedMotor.stop();
		}
	}

	private void stopLiftUp()
	{
		if (!forkOverride)
		{
			encodedMotor.stop();
			lockLift();
		} else
		{
			encodedMotor.stop();
		}
	}

	@Override
	public void goToBottomLimit()
	{
		unlockLift();
		while (!isAtBottom() && !forkOverride)
		{
			goDownSafe();
			SmartDashboard.putNumber("Distance", encodedMotor.getDistance());
		}
		forkOverride = false;

		//stopLiftDown();
		stop();
	}
	
	private void lockLift()
	{
		latch.engage();
	}

	private void unlockLift()
	{
		latch.disengage();
		encodedMotor.up(LIFTER_UP_SPEED);
		waitServo();
		encodedMotor.stop();
	}

	private void waitServo()
	{
		long time = clock.getTimeInMillis();
		while (clock.getTimeInMillis() < time + UNLOCK_TIME)
		{
			// Wait for unlock
		}
	}

	@Override
	public void goToTopLimit()
	{
		unlockLift();
		while (!isAtTop() && !forkOverride)
		{
			encodedMotor.up(LIFTER_UP_SPEED);
			SmartDashboard.putNumber("Distance", encodedMotor.getDistance());
		}
		forkOverride = false;
		//stopLiftUp();
		stop();
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousToteLength()
	{
		previousLength(TOTE_HEIGHT_IN);
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextToteLength()
	{
		nextLength(TOTE_HEIGHT_IN);
	}

	private void nextLength(double encoderCount)
	{
		if (!isAtTop())
		{
			double previousDistance = encodedMotor.getDistance();
			unlockLift();
			while (!isAtTop() && encodedMotor.getDistance() < (previousDistance + encoderCount) && !forkOverride)
			{
				encodedMotor.up(LIFTER_UP_SPEED);
			}
		}
		forkOverride = false;
		stopLiftUp();
	}

	private void previousLength(double encoderCount)
	{
		if (!isAtBottom())
		{
			double previousDistance = encodedMotor.getDistance();
			unlockLift();
			while (!isAtBottom() && encodedMotor.getDistance() > (previousDistance - encoderCount) && !forkOverride)
			{
				goDownSafe();
				SmartDashboard.putNumber("Distance", encodedMotor.getDistance());
			}
		}
		forkOverride = false;
		stopLiftDown();
	}

	private void goDownSafe()
	{
		if (encodedMotor.getDistance() <= 3)
		{
			encodedMotor.down(CALIBRATE_SPEED);
		} else
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		}

	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousBinLength()
	{
		previousLength(BIN_HEIGHT_IN);
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextBinLength()
	{
		nextLength(BIN_HEIGHT_IN);
	}

	@Override
	public void toDisplay()
	{
		Display.getInstance().setForkliftData(encodedMotor.getDistance(), encodedMotor.getRate(), isAtTop(), isAtBottom());
	}

	@Override
	public void calibrate()
	{
		unlockLift();
		while (!isAtBottom())
		{
			goDownSafe();
			SmartDashboard.putNumber("Distance", encodedMotor.getDistance());
		}

		stopLiftDown();

		encodedMotor.reset();
		isCalibrated = true;
	}

	public void stopAction()
	{
		forkOverride = true;
	}

	public void up()
	{
		latch.disengage();
		
		if (!isAtTop())
		{
			encodedMotor.up(LIFTER_UP_SPEED);
		}
	}

	public void down()
	{
		unlockLift();
		
		if (!isAtBottom())
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		}
	}
	
	private boolean isAtBottom()
	{
		return (isCalibrated ? (botSwitch.isHit() || encodedMotor.getPosition() <= LIFTER_BOTTOM_LIMIT) : botSwitch.isHit()) && !forkOverride;
	}

	private boolean isAtTop()
	{
		return ((isCalibrated ? (topSwitch.isHit() || encodedMotor.getPosition() >= LIFTER_TOP_LIMIT) : topSwitch.isHit())) && !forkOverride;
	}

}