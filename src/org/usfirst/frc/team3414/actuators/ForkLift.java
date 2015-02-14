package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.autonomous.SwitchPositions;
import org.usfirst.frc.team3414.sensors.Constants;
import org.usfirst.frc.team3414.sensors.HardwarePorts;
import org.usfirst.frc.team3414.sensors.MyEncoder;
import org.usfirst.frc.team3414.sensors.MyLimitSwitch;
import org.usfirst.frc.team3414.sensors.MySolenoid;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class ForkLift implements ILiftAssist, HardwarePorts, Constants
{
	
	private IEncodedMotor encodedMotor;
	private MyEncoder liftEncoder;
	private MySolenoid gripSolenoid;
	private MyLimitSwitch topSwitch;
	private MyLimitSwitch botSwitch;
	
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
		speedCont = new Talon(1);
	    liftEncoder = new MyEncoder(LIFT_ENCODER_A, LIFT_ENCODER_B);
		encodedMotor = new EncodedMotor(speedCont, liftEncoder, NOT_INVERSE);
		topSwitch = new MyLimitSwitch(LIMIT_SWITCH_TOP, NOT_INVERSE);
		botSwitch = new MyLimitSwitch(LIMIT_SWITCH_BOTTOM, NOT_INVERSE);
		gripSolenoid = new MySolenoid(LIFTER_GRIP);
		isEncZeroed = false;
		gripSolenoid.disable();
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
		    gripSolenoid.disable();
		    encodedMotor.forward(1, 10);
		}
		this.stop();
		gripSolenoid.enable();
	}

	public void goToBottom()
	{
		while (!joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
			if ((encodedMotor.getEncoderPosition() > bottomEncPo))
			{
				gripSolenoid.disable();
			    encodedMotor.backward(1, 10);
			}
			if ((encodedMotor.getEncoderPosition() < bottomEncPo))
			{
				gripSolenoid.enable();
			    encodedMotor.forward(1, 10);
			}
		}
		this.stop();
		gripSolenoid.enable();
	}

	public void nextToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + toteEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    gripSolenoid.disable();
		    encodedMotor.forward(1, 10);
		}
		this.stop();
		gripSolenoid.enable();
	}

	public void previousToteLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - toteEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	gripSolenoid.disable();
			encodedMotor.backward(1, 10);
		}
		this.stop();
		gripSolenoid.enable();
	}

	public void nextBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() < current + binEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	gripSolenoid.disable();
			encodedMotor.forward(1, 10);
		}
		this.stop();
		gripSolenoid.enable();
	}

	public void previousBinLength()
	{
		double current = encodedMotor.getEncoderPosition();
		while ((encodedMotor.getEncoderPosition() > current - binEncPo) && !joystickOverride && topSwitch.get() == SwitchPositions.OFF && botSwitch.get() == SwitchPositions.OFF)
		{
		    	gripSolenoid.disable();
			encodedMotor.backward(1, 10);
		}
		this.stop();
		gripSolenoid.enable();
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
		    	gripSolenoid.disable();
			encodedMotor.backward(.5, 20);
			if (botSwitch.get()  == SwitchPositions.ON)
			{
				liftEncoder.reset();
				isEncZeroed = true;
			}
		}
		this.goToBottom();
	}
}
