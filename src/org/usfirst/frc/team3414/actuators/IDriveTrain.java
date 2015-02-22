package org.usfirst.frc.team3414.actuators;



/**
 * implemented by MecanumDrive.java
 */
public  interface IDriveTrain 
{
	
	public void move(double magnitude, double angle, double rotation) ;
	
	public void rotate(double degrees, boolean clockWise);

	
	public void stop();
	
	public void moveConstantVelocity(double speed, double direction);
	
	public void rotateConstantVelocity(boolean clockWise);
	
	public void toDisplay();
}
	
	

