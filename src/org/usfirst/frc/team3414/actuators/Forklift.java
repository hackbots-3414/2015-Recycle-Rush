package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.*;
import org.usfirst.frc.team3414.teleop.Display;

import edu.wpi.first.wpilibj.Timer;

public class Forklift extends Thread implements ILiftAssist, HardwarePorts, Constants
{

	private IEncodedMotor encodedMotor;
	private IServo latch;
	private ILimitSwitch topSwitch;
	private ILimitSwitch botSwitch;
	private boolean isEncZeroed;
	//private int[] lifterState = { LOWER_LIMIT, GROUND, ONE, TWO, THREE, FOUR, UPPER_LIMIT };
	private int[] lifterState;
	
	private int goToPosition = 0;
	//private double gravityTestMotorSpeed = 0;

	private static Forklift singleton = null;

	protected Forklift(IEncodedMotor motor, ILimitSwitch topSwitch, ILimitSwitch bottomSwitch, IServo servo)
	{
		encodedMotor = motor;
		this.topSwitch = topSwitch;
		this.botSwitch = bottomSwitch;
		latch = servo;
		isEncZeroed = false;
		latch.disengage();
	}

	public void run()
	{
		/*
		//TODO: GRAVITY TEST
		encodedMotor.up(gravityTestMotorSpeed);
		*/
		
		
		// Main Lifter Function
		if ((encodedMotor.getPosition() < (lifterState[goToPosition] - ALLOWANCE)) && !topSwitch.isHit())
		{
			latch.disengage();
			encodedMotor.up(LIFTER_UP_SPEED);
		} else if ((encodedMotor.getPosition() > (lifterState[goToPosition] + ALLOWANCE)) && !botSwitch.isHit())
		{
			latch.disengage();
			encodedMotor.up(LIFTER_UP_SPEED);
			Timer.delay(0.5);
			encodedMotor.down(LIFTER_DOWN_SPEED);
		} else if ((encodedMotor.getPosition() >= (lifterState[goToPosition] - ALLOWANCE))
				&& (encodedMotor.getPosition() <= (lifterState[goToPosition] + ALLOWANCE)))
		{
			latch.disengage();
			encodedMotor.up(0.0); // TODO: Allowance for Gravity
		} else
		{
			latch.engage();
			encodedMotor.stop();
		}

		// Reset Encoder
		if (!isEncZeroed && botSwitch.isHit())
		{
			encodedMotor.reset();
			isEncZeroed = true;
		}
	}

	@Override
	public void goToGround()
	{
		goToPosition = 1;
	}

	@Override
	public void goToBottomLimit()
	{
		if (!isEncZeroed)
		{
			encodedMotor.down(LIFTER_DOWN_SPEED);
		} else
		{
			goToPosition = 0;
		}
	}

	@Override
	public void goToTopLimit()
	{
		goToPosition = (lifterState.length - 1);

	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousToteLength()
	{
		if (goToPosition == (lifterState.length - 1))
		{
			goToPosition -= 2;
		} else if (goToPosition > 1) // Position 2 or Higher
		{
			goToPosition--;
		}

	}
	
	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextToteLength()
	{
		if (goToPosition == 0)
		{
			goToPosition += 2;
		} else if (goToPosition < (lifterState.length - 2)) // Position 4 or
															// Lower
		{
			goToPosition++;
		}

	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void previousBinLength()
	{
		if (goToPosition == (lifterState.length - 1))
		{
			goToPosition -= 3;
		} else if (goToPosition > 2) // Position 3 or Higher
		{
			goToPosition -= 2;
		}
	}

	@Override
	/**
	 * Such will NOT hit the limit switch
	 */
	public void nextBinLength()
	{
		if (goToPosition == 0)
		{
			goToPosition += 3;
		} else if (goToPosition < (lifterState.length - 3)) // Position 3 or
															// Lower
		{
			goToPosition += 2;
		}

	}

	/*
	public void setSpeed(double speed) {
		this.gravityTestMotorSpeed = speed;
	}
	*/
	
	public void toDisplay()
	{
		Display.getInstance().setForkliftData(goToPosition, encodedMotor.getPosition(), encodedMotor.getRate(), topSwitch.isHit(), botSwitch.isHit());
	}

}
