package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.Servo;

public class MyServo implements IServo, Constants
{
	Servo servo;

	public MyServo(int channel)
	{
		servo = new Servo(channel);
	}

	public void engage()
	{
		servo.set(SERVO_LATCHED);
	}

	public void disengage()
	{
		servo.set(SERVO_UNLATCHED);
	}

	public boolean getEngaged()
	{
		if (this.getAngle() == SERVO_LATCHED)
		{
			return true;
		} else
		{
			return false;
		}
	}

	public double getAngle()
	{
		return servo.getAngle();
	}

}
