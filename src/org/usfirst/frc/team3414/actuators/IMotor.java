package org.usfirst.frc.team3414.actuators;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public interface IMotor 
{
	public abstract void stop();

    public abstract void backward(double speed);

    public abstract void forward(double speed, int motorStep);

    public abstract void forward(double speed);

    public abstract void backward(double speed, int motorStep);

    public abstract double getSpeed();
	
}



