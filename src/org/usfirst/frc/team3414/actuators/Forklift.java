package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.*;
import org.usfirst.frc.team3414.teleop.Display;

public class Forklift implements ILiftAssist
{
	private static final int UNLOCK_TIME = 300; // previously 500
	private static final double LIFTER_UP_SPEED = 1.0;
	private static final double LIFTER_DOWN_SPEED = 1.0;
	private static final double CALIBRATE_SPEED = 0.3;

	private static final double TOTE_HEIGHT_IN = 14; // previously 12
	private static final double BIN_HEIGHT_IN = 28.75;

	private IEncodedMotor encodedMotor;
	private IServo latch;
	private ILimitSwitch topSwitch;
	private ILimitSwitch botSwitch;
	private boolean isCalibrated;
	private IClock clock;

	private boolean forkOverride;
	private boolean upInit;
	private boolean downInit;
	private boolean stopInit;

	protected Forklift(IEncodedMotor motor, ILimitSwitch topSwitch, ILimitSwitch bottomSwitch, IServo servo, IClock clock)
	{
		isCalibrated = false;
		forkOverride = false;
		upInit = true;
		downInit = true;
		stopInit = true;
		encodedMotor = motor;
		this.topSwitch = topSwitch;
		this.botSwitch = bottomSwitch;
		latch = servo;
		latch.disengage();
		this.clock = clock;
	}

	/**
	 * PUBLIC FUNCTION To be called periodically
	 */
	public void upLift()
	{
		if (!isAtTop() && !forkOverride)
		{
			if (upInit)
			{
				unlockLift(); // WILL WAIT A SEC AND THEN KEEP GOING
				upInit = false;
				downInit = true;
				stopInit = true;
			}
			encodedMotor.up(LIFTER_UP_SPEED);
		} else
		{
			stopLift();
		}
	}

	/**
	 * PUBLIC FUNCTION To be called periodically
	 */
	public void downLift()
	{

		if (!isAtBottom() && !forkOverride)
		{
			if (downInit)
			{
				unlockLiftMotion();
				downInit = false;
				upInit = true;
				stopInit = true;
			}

			goDownSafe();
		} else
		{
			stopLift();
		}
	}

	/**
	 * PUBLIC FUNCTION To be called periodically
	 */
	public void stopLift()
	{
		stop();
		if (stopInit)
		{
			lockLift();
			stopInit = false;
			upInit = true;
			downInit = true;
		}

	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void goToBottomLimit()
	{
		while (!isAtBottom() && !forkOverride)
		{
			downLift();

		}
		stopLift();
	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void goToTopLimit()
	{
		while (!isAtTop() && !forkOverride)
		{
			upLift();
		}
		stopLift();
	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void previousToteLength()
	{
		previousLength(TOTE_HEIGHT_IN);
	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void nextToteLength()
	{
		nextLength(TOTE_HEIGHT_IN);
	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void previousBinLength()
	{
		previousLength(BIN_HEIGHT_IN);
	}

	@Override
	/**
	 * PUBLIC FUNCTION
	 */
	public void nextBinLength()
	{
		nextLength(BIN_HEIGHT_IN);
	}

	/**
	 * PRIVATE FUNCTION
	 */
	private void unlockLiftMotion()
	{
		latch.disengage();
		encodedMotor.up(LIFTER_UP_SPEED);
		waitServo();
		stop();
	}

	/**
	 * PRIVATE FUNCTION
	 */
	private void nextLength(double encoderCount)
	{
		if (!isAtTop())
		{
			double previousDistance = encodedMotor.getDistance();
			while (!isAtTop() && encodedMotor.getDistance() < (previousDistance + encoderCount) && !forkOverride)
			{
				upLift();
			}
		}
		stopLift();
	}

	/**
	 * PRIVATE FUNCTION
	 */
	private void previousLength(double encoderCount)
	{
		if (!isAtBottom())
		{
			double previousDistance = encodedMotor.getDistance();
			while (!isAtBottom() && encodedMotor.getDistance() > (previousDistance - encoderCount) && !forkOverride)
			{
				downLift();
			}
		}
		stopLift();
	}

	/**
	 * PRIVATE FUNCTION
	 */
	private void goDownSafe()
	{
		if (Math.abs(encodedMotor.getDistance()) <= 3 && isCalibrated)
		{
			encodedMotor.down(CALIBRATE_SPEED);
		} else
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		}

	}

	/**
	 * PRIVATE BASELINE FUNCTION
	 */
	private void lockLift()
	{
		latch.engage();
		waitServo();
	}

	/**
	 * PRIVATE BASELINE FUNCTION
	 */
	private void unlockLift()
	{
		latch.disengage();
		waitServo();
	}

	/**
	 * PRIVATE BASELINE FUNCTION
	 */
	private void waitServo()
	{
		long time = clock.getTimeInMillis();
		while (clock.getTimeInMillis() < time + UNLOCK_TIME)
		{
			// Wait for unlock
		}
	}

	/**
	 * PRIVATE BASELINE FUNCTION
	 */
	private void stop()
	{
		encodedMotor.stop();
	}

	// OTHER

	@Override
	public void toDisplay()
	{
		Display.getInstance().setForkliftData(encodedMotor.getDistance(), encodedMotor.getRate(), topSwitch.isHit(), botSwitch.isHit());
	}

	@Override
	public void calibrate()
	{
		unlockLiftMotion();
		while (!isAtBottom())
		{
			encodedMotor.down(CALIBRATE_SPEED);
		}

		stop();

		encodedMotor.reset();
		isCalibrated = true;

	}

	/**
	 * DOES NOT OVERRIDE THE SERVO
	 */
	public void setEStopAllAction(boolean override)
	{
		forkOverride = override;
	}

	private boolean isAtBottom()
	{
		return botSwitch.isHit();
		// return (isCalibrated ? (botSwitch.isHit() ||
		// encodedMotor.getPosition() <= LIFTER_BOTTOM_LIMIT) :
		// botSwitch.isHit()) && !forkOverride;
	}

	private boolean isAtTop()
	{
		return topSwitch.isHit();
		// return ((isCalibrated ? (topSwitch.isHit() ||
		// encodedMotor.getPosition() >= LIFTER_TOP_LIMIT) : topSwitch.isHit()))
		// && !forkOverride;
	}
}


/*package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.*;
import org.usfirst.frc.team3414.teleop.Display;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Forklift implements ILiftAssist
{
	private static final int UNLOCK_TIME = 300; //previously 500
	private static final double LIFTER_UP_SPEED = 1.0;
	private static final double LIFTER_DOWN_SPEED = 1.0;
	private static final double CALIBRATE_SPEED = 0.3;
	private static final double LIFTER_TOP_LIMIT = 19500;
	private static final double LIFTER_BOTTOM_LIMIT = 200;

	private static final double TOTE_HEIGHT_IN = 14; //previously 12
	private static final double BIN_HEIGHT_IN = 28.75;

	private IEncodedMotor encodedMotor;
	private IServo latch;
	private ILimitSwitch topSwitch;
	private ILimitSwitch botSwitch;
	private boolean isCalibrated;
	private IClock clock;

	private boolean forkOverride;
	private boolean upInit;
	private boolean downInit;
	private boolean stopInit;

	protected Forklift(IEncodedMotor motor, ILimitSwitch topSwitch, ILimitSwitch bottomSwitch, IServo servo, IClock clock)
	{
		isCalibrated = false;
		forkOverride = false;
		upInit = true;
		downInit = true;
		stopInit = true;
		encodedMotor = motor;
		this.topSwitch = topSwitch;
		this.botSwitch = bottomSwitch;
		latch = servo;
		latch.disengage();
		this.clock = clock;
	}

	// PUBLIC FUNCTIONS
	*//**
	 * To be called periodically
	 *//*
	public void upLift()
	{
		if (!forkOverride)
		{
			if (upInit)
			{
				unlockLift();
				stopInit = true;
				upInit = false;
			}

			if (!isAtTop())
			{
				encodedMotor.up(LIFTER_UP_SPEED);
			} else {
				stopLift();
			}
		} else
		{
			stop();
		}
	}

	*//**
	 * To be called periodically
	 *//*
	public void downLift()
	{
		if (!forkOverride)
		{
			if (downInit)
			{
				unlockLiftMotion();
				stopInit = true;
				downInit = false;
			}

			if (!isAtBottom())
			{
				goDownSafe();
			} else {
				stopLift();
			}
		} else
		{
			stop();
		}
	}

	*//**
	 * To be called periodically
	 *//*
	public void stopLift()
	{
		stop();
		if (stopInit)
		{
			lockLift();
			stopInit = false;
		}
		upInit = true;
		downInit = true;

	}

	@Override
	public void goToBottomLimit()
	{
		unlockLiftMotion();
		while (!isAtBottom() && !forkOverride)
		{
			goDownSafe();

		}
		stop();
		encodedMotor.reset();
//		if (!forkOverride)
//		{
			lockLift();
//		}
	}

	@Override
	public void goToTopLimit()
	{
		unlockLift();
		while (!isAtTop() && !forkOverride)
		{
			encodedMotor.up(LIFTER_UP_SPEED);
		}
		stop();
//		if (!forkOverride)
//		{
			lockLift();
//		}
	}

	@Override
	public void previousToteLength()
	{
		previousLength(TOTE_HEIGHT_IN);
	}

	@Override
	public void nextToteLength()
	{
		nextLength(TOTE_HEIGHT_IN);
	}

	@Override
	public void previousBinLength()
	{
		previousLength(BIN_HEIGHT_IN);
	}

	@Override
	public void nextBinLength()
	{
		nextLength(BIN_HEIGHT_IN);
	}

	// PRIVATE OPERATIONS

	private void unlockLiftMotion()
	{
		latch.disengage();
		encodedMotor.up(LIFTER_UP_SPEED);
		waitServo();
		encodedMotor.stop();
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
		stop();
//		if (!forkOverride)
//		{
			lockLift();
//		}
	}

	private void previousLength(double encoderCount)
	{
		if (!isAtBottom())
		{
			double previousDistance = encodedMotor.getDistance();
			unlockLiftMotion();
			while (!isAtBottom() && encodedMotor.getDistance() > (previousDistance - encoderCount) && !forkOverride)
			{
				goDownSafe();
			}
		}
		stop();
//		if (!forkOverride)
//		{
			lockLift();
//		}
	}

	private void goDownSafe()
	{
		if (Math.abs(encodedMotor.getDistance()) <= 3 && isCalibrated)
		{
			encodedMotor.down(CALIBRATE_SPEED);
		} else
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		}

	}

	// PRIVATE BASELINE FUNCTIONS

	private void lockLift()
	{
		latch.engage();
		waitServo();
	}

	private void unlockLift()
	{
		latch.disengage();
		waitServo();
	}

	private void waitServo()
	{
		long time = clock.getTimeInMillis();
		while (clock.getTimeInMillis() < time + UNLOCK_TIME)
		{
			// Wait for unlock
		}
	}

	private void stop()
	{
		encodedMotor.stop();
	}

	// OTHER

	@Override
	public void toDisplay()
	{
		Display.getInstance().setForkliftData(encodedMotor.getDistance(), encodedMotor.getRate(), topSwitch.isHit(), botSwitch.isHit());
	}

	@Override
	public void calibrate()
	{
		unlockLiftMotion();
		while (!isAtBottom())
		{
			encodedMotor.down(CALIBRATE_SPEED);
		}

		stop();

			encodedMotor.reset();
			isCalibrated = true;
		
	}

	public void setEStopAllAction(boolean override)
	{
		forkOverride = override;
	}

	private boolean isAtBottom()
	{
		return botSwitch.isHit();
		//return (isCalibrated ? (botSwitch.isHit() || encodedMotor.getPosition() <= LIFTER_BOTTOM_LIMIT) : botSwitch.isHit()) && !forkOverride;
	}

	private boolean isAtTop()
	{
		return topSwitch.isHit();
		//return ((isCalibrated ? (topSwitch.isHit() || encodedMotor.getPosition() >= LIFTER_TOP_LIMIT) : topSwitch.isHit())) && !forkOverride;
	}
}*/