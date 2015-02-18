package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.IServo;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Servo implements IServo
{
	private static final double SERVO_LATCHED = 0.9;
	private static final double SERVO_UNLATCHED = 0.0;
	
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
		servo.set(SmartDashboard.getNumber("Latched", 1.0));
		isLatched = true;
	}

	public void disengage()
	{
		servo.set(SmartDashboard.getNumber("UN Latched", .7));
		isLatched = false;
	}

	public boolean getEngaged()
	{
		return isLatched;
	}
}