package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.IServo;

public class Servo implements IServo
{
	private static final double SERVO_LATCHED = 0.0;
	private static final double SERVO_UNLATCHED = 0.5;
	
	private boolean isLatched;
	
	edu.wpi.first.wpilibj.Servo servo;

	public Servo(int channel)
	{
		servo = new edu.wpi.first.wpilibj.Servo(channel);
		servo.set(SERVO_UNLATCHED);
		isLatched = false;
	}

	public void engage()
	{
		servo.set(SERVO_LATCHED);
		isLatched = true;
	}

	public void disengage()
	{
		servo.set(SERVO_UNLATCHED);
		isLatched = false;
	}

	public boolean getEngaged()
	{
		return isLatched;
	}
}