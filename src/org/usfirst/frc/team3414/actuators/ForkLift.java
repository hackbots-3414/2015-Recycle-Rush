package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;
import org.usfirst.frc.team3414.sensors.MyEncoder;
import org.usfirst.frc.team3414.sensors.LimitSwitch;
import org.usfirst.frc.team3414.sensors.MySolenoid;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class ForkLift implements ILiftAssist
{
	private IEncodedMotor encodedMotor;
	private MyEncoder encoder;
	private Encoder encode;
	private MySolenoid solenoid;
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
	    	encode = new Encoder(4,5);
	    	encoder = new MyEncoder(encode);
		encodedMotor = new EncodedMotor(speedCont, encoder, false);
		topSwitch = new LimitSwitch(1, false);
		botSwitch = new LimitSwitch(2, false);
		solenoid = new MySolenoid(3);
		isEncZeroed = false;
		solenoid.set(false);
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

	public void goToTop()
	{
		while ((encodedMotor.getEncoderPosition() < topEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    solenoid.set(false);
		    encodedMotor.forward(1, 10);
		}
		this.stop();
		solenoid.set(true);
	}

	public void goToBottom()
	{
		while (!joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
			if ((encodedMotor.getEncoderPosition() > bottomEncPo))
			{
			    solenoid.set(false);
			    encodedMotor.backward(1, 10);
			}
			if ((encodedMotor.getEncoderPosition() < bottomEncPo))
			{
			    solenoid.set(false);
			    encodedMotor.forward(1, 10);
			}
		}
		this.stop();
		solenoid.set(true);
	}

	public void nextToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + toteEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    solenoid.set(false);
		    encodedMotor.forward(1, 10);
		}
		this.stop();
		solenoid.set(true);
	}

	public void previousToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - toteEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	solenoid.set(false);
			encodedMotor.backward(1, 10);
		}
		this.stop();
		solenoid.set(true);
	}

	public void nextBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + binEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	solenoid.set(false);
			encodedMotor.forward(1, 10);
		}
		this.stop();
		solenoid.set(true);
	}

	public void previousBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - binEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	solenoid.set(false);
			encodedMotor.backward(1, 10);
		}
		this.stop();
		solenoid.set(true);
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
		    	solenoid.set(false);
			encodedMotor.backward(.5, 20);
			if (botSwitch.get()  == SwitchPositions.ON)
			{
				encoder.reset();
				isEncZeroed = true;
			}
		}
		this.goToBottom();
	}
}
