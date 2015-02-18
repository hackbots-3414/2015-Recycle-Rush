package org.usfirst.frc.team3414.actuators;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface IDriveTrain 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void move(double magnitude, double angle, double rotation) ;
	
	public void rotate(double degrees, boolean clockWise);

	
	public void stop();
	
	public void moveConstantVelocity(double speed, double direction);
	
	public void rotateConstantVelocity(boolean clockWise);
	
	public void toLog();
}
	
	

