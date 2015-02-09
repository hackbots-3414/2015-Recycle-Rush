package org.usfirst.frc.team3414.actuators;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public interface IMotor 
{
	public void stop();

    public void backward(double speed);

    public void forward(double speed, int motorStep);

    public void forward(double speed);

    public void backward(double speed, int motorStep);

    public double getSpeed();
	
}



