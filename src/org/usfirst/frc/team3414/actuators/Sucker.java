package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sucker implements ISucker
{
	IMotor leftMotor;
	IMotor rightMotor;

	public Sucker(IMotor leftShooterMotor, IMotor rightShooterMotor)
	{
		SmartDashboard.putBoolean("got sucker", true);
		leftShooterMotor = leftMotor;
		rightShooterMotor = rightMotor;
	}

	final double MOTOR_SPEED = 1.0;

	public void in()
	{
		leftMotor.down(MOTOR_SPEED);
		rightMotor.up(MOTOR_SPEED);
		SmartDashboard.putBoolean("Doing in", true);
	}

	public void out()
	{
		
		leftMotor.up(MOTOR_SPEED);
		rightMotor.down(MOTOR_SPEED);
		SmartDashboard.putBoolean("Doing out", true);
	}

	public void stop()
	{
		leftMotor.up(0.0);
		rightMotor.down(0.0);
		SmartDashboard.putBoolean("Doing stop", true);
	}

}