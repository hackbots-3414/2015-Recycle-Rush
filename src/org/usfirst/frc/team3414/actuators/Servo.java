package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.IServo;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Servo implements IServo
{
	private static final double SERVO_LATCHED = 1.0;
	private static final double SERVO_UNLATCHED = 0.7;
	
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
		servo.set(SmartDashboard.getNumber("Latched", SERVO_LATCHED));
		isLatched = true;
	}

	public void disengage()
	{
		servo.set(SmartDashboard.getNumber("UN Latched", SERVO_UNLATCHED));
		isLatched = false;
	}

	public boolean getEngaged()
	{
		return isLatched;
	}
}