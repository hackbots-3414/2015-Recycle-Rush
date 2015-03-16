package org.usfirst.frc.team3414.actuators;

public class Sucker implements ISucker
{
    IMotor leftMotor;
    IMotor rightMotor;
    
    public Sucker(IMotor leftShooterMotor, IMotor rightShooterMotor)
    {
	leftShooterMotor = leftMotor;
	rightShooterMotor = rightMotor;
    }
    
    public void In(double speed)
    {
	leftMotor.down(speed);
	rightMotor.up(speed);
    }
    
    public void Out(double speed)
    {
	leftMotor.up(speed);
	rightMotor.down(speed);
    }
    
}
