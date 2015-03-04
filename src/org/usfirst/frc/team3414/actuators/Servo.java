package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.actuators.IServo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Servo implements IServo
{
	private static final double SERVO_LATCHED_BIG_BOY = 1.0;
	private static final double SERVO_UNLATCHED_BIG_BOY = 0.7;
	private static final double SERVO_LATCHED_HONEY_BADGER = 1.0;
	private static final double SERVO_UNLATCHED_HONEY_BADGER = 0.7;
	private double dashboardSet = 0.5;
	
	private boolean isLatched;
	
	edu.wpi.first.wpilibj.Servo servo;

	public Servo(int channel)
	{
		servo = new edu.wpi.first.wpilibj.Servo(channel);
		//servo.set(SERVO_UNLATCHED_BIG_BOY);
//		servo.set(SERVO_UNLATCHED_HONEY_BADGER);
		dashboardSetServo();
		isLatched = false;
	}

	public void engage()
	{
		//servo.set(SERVO_LATCHED_BIG_BOY);
		//servo.set(SERVO_LATCHED_HONEY_BADGER);
		dashboardSetServo();
		isLatched = true;
	}

	public void disengage()
	{
		//servo.set(SERVO_UNLATCHED_BIG_BOY);
		dashboardSetServo();
//		servo.set(SERVO_UNLATCHED_HONEY_BADGER);
		isLatched = false;
	}

	public boolean getEngaged()
	{
		return isLatched;
	}
	
	/**
	 * Default = 0.5
	 */
	public void dashboardSetServo() {
		servo.set(SmartDashboard.getNumber("Servo Set to: ", dashboardSet));
	}
}