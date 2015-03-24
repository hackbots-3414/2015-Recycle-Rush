package org.usfirst.frc.team3414.actuators;

public class Sucker implements ISucker
{
	private IMotor leftMotor;
	private IMotor rightMotor;

	public Sucker(IMotor leftShooterMotor, IMotor rightShooterMotor)
	{
		leftMotor = leftShooterMotor;
		rightMotor = rightShooterMotor;
	}

	private final double MOTOR_SPEED = 1.0;

	public void in()
	{
		//leftMotor.down(MOTOR_SPEED);
		leftMotor.up(MOTOR_SPEED);
		rightMotor.up(MOTOR_SPEED);
	}

	public void out()
	{
		leftMotor.down(MOTOR_SPEED);
		//leftMotor.up(MOTOR_SPEED);
		rightMotor.down(MOTOR_SPEED);
	}
	
	public void right() {
		leftMotor.down(MOTOR_SPEED);
		rightMotor.up(MOTOR_SPEED);
	}
	
	public void left() {
		leftMotor.up(MOTOR_SPEED);
		rightMotor.down(MOTOR_SPEED);
	}

	public void stop()
	{
		leftMotor.up(0.0);
		rightMotor.down(0.0);
	}
	
	public void setInSpeed(double speed) {
		leftMotor.up(speed);
		rightMotor.up(speed);
	}

}