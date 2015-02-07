package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.MyEncoder;
import org.usfirst.frc.team3414.sensors.LimitSwitch;
import edu.wpi.first.wpilibj.SpeedController;

public class ForkLift implements ILiftAssist
{
	private IEncodedMotor encodedMotor;
	private MyEncoder encoder;
	LimitSwitch topSwitch;
	LimitSwitch botSwitch;
	private SpeedController speedCont;

	private double topEncPo;
	private double bottomEncPo;
	private double binEncPo;
	private double toteEncPo;
	private boolean joystickOverride;
	private boolean isEncZeroed;

	private static ForkLift singleton = null;

	private ForkLift()
	{
		encodedMotor = new EncodedMotor(speedCont, encoder, false);
		topSwitch = new LimitSwitch(1, false);
		botSwitch = new LimitSwitch(2, false);
		isEncZeroed = false;
	}

	public static ForkLift createInstance()
	{
		if (singleton == null)
		{
			singleton = new ForkLift();
		}

		return singleton;
	}

	public static ForkLift getInstance()
	{
		if (singleton == null)
		{
			throw new NullPointerException("ForkLift hasn't been created yet");
		}

		return singleton;
	}

	/*
	 * public ForkLift(ICanMotor pCanMotor, int forkLiftPort) { this.canMotor =
	 * pCanMotor; // tempTopSwitch = topSwitch; // tempBottomSwitch =
	 * bottomSwitch;
	 * 
	 * }
	 * 
	 * public ForkLift(ICanMotor tempCanMotor, int forkLiftPort) { tempCanMotor
	 * = canMotor;
	 * 
	 * // Speed setpoint for the closed loop system. speedSetpoint = 1.0; }
	 */
	public void goToTop()
	{
		while ((encodedMotor.getEncoderPosition() < topEncPo) && !joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			encodedMotor.forward(1, 10);
		}
		this.stop();
	}

	public void goToBottom()
	{
		while (!joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			if ((encodedMotor.getEncoderPosition() > bottomEncPo))
			{
				encodedMotor.backward(1, 10);
			}
			if ((encodedMotor.getEncoderPosition() < bottomEncPo))
			{
				encodedMotor.forward(1, 10);
			}
		}
		this.stop();

	}

	public void nextToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + toteEncPo) && !joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			encodedMotor.forward(1, 10);
		}
		this.stop();
	}

	public void previousToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - toteEncPo) && !joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			encodedMotor.backward(1, 10);
		}
		this.stop();
	}

	public void nextBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + binEncPo) && !joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			encodedMotor.forward(1, 10);
		}
		this.stop();
	}

	public void previousBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - binEncPo) && !joystickOverride && !topSwitch.get() && !botSwitch.get())
		{
			encodedMotor.backward(1, 10);
		}
		this.stop();
	}

	public void stop()
	{
		double rampRate = (encodedMotor.getSpeed() / 10);

		for (int i = 0; i <= 10; i++)
		{
			encodedMotor.forward(encodedMotor.getSpeed() - (rampRate * i));

			try
			{
				Thread.sleep(300);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void goToBotLimSwitch()
	{
		while (!joystickOverride && !isEncZeroed)
		{
			encodedMotor.backward(.5, 20);
			if (botSwitch.get())
			{
				encoder.reset();
				isEncZeroed = true;
			}
		}
		this.goToBottom();
	}
}
