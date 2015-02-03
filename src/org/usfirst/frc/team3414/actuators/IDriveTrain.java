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
	
	public void move(double angle, double velocity, double rotation) ;
	
	public void rotateToDegrees(double degrees);
	
	public void stop();
}
	
	

