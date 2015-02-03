package org.usfirst.frc.team3414.actuators;
import org.usfirst.frc.team3414.actuators.IMotor;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DoubleMotor implements IMotor
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
        motorOne.backward(speed);
        motorTwo.backward(speed);
    }

    public void forward(double speed, int motorStep)
    {
        motorOne.forward(speed, motorStep);
        motorTwo.forward(speed, motorStep);
    }

    public void forward(double speed)
    {
        motorOne.forward(speed);
        motorTwo.forward(speed);
    }

    public void backward(double speed, int motorStep)
    {
        motorOne.forward(speed, motorStep);
        motorTwo.forward(speed, motorStep);
    }

    public double getSpeed()
    {
        return (motorOne.getSpeed() + motorTwo.getSpeed()) / 2;
    }
}