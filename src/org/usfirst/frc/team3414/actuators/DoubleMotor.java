package org.usfirst.frc.team3414.actuators;
import org.usfirst.frc.team3414.actuators.IMotor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DoubleMotor
{
	
	private final IMotor motorOne;
    private final IMotor motorTwo;

    public DoubleMotor(Motor motorOne, Motor motorTwo)
    {
        this.motorOne = motorOne;
        this.motorTwo = motorTwo;
    }

    public void stop()
    {
        motorOne.stop();
        motorTwo.stop();
    }

    public void backward(double speed)
    {
        motorOne.down(speed);
        motorTwo.down(speed);
    }

    public void forward(double speed, int motorStep)
    {
        motorOne.upGradual(speed, motorStep);
        motorTwo.upGradual(speed, motorStep);
    }

    public void forward(double speed)
    {
        motorOne.up(speed);
        motorTwo.up(speed);
    }

    public void backward(double speed, int motorStep)
    {
        motorOne.downGradual(speed, motorStep);
        motorTwo.downGradual(speed, motorStep);
    }

    public double getSpeed()
    {
        return (motorOne.getSpeed() + motorTwo.getSpeed()) / 2;
    }
}