package org.usfirst.frc.team3414.sensors;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;


/**
 * 
 * This implements the built-in accelerometer inside the RoboRIO and allows it's values to be zeroed out.
 * 
 * @generated
 */

public class Accelerometer implements IMeasureAcceleration
{
	
	double xOffset = 0;
	double yOffset = 0;
	double zOffset = 0;
	
	BuiltInAccelerometer accel;
	
	/**
	 * 
	 * The constructor for the accelerometer.
	 * 
	 * @generated
	 */
	protected Accelerometer(){
		accel = new BuiltInAccelerometer();
	}

	/**
	 * 
	 * Gets the acceleration on the X axis.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double getAccelX() {
		return (accel.getX() + xOffset); 
	}
	
	/**
	 * 
	 * Gets the acceleration on the Y axis.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double getAccelY() {
		return (accel.getY() + yOffset); 
	}
	
	/**
	 * 
	 * Gets the acceleration on the Z axis.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public double getAccelZ() {
		return (accel.getZ() + zOffset); 
	}
	
	/**
	 * 
	 * Zeroes the accelerometer values.
	 * 
	 * @generated
	 * @ordered
	 */
	
	public void reset() {
		xOffset = accel.getX();
		yOffset = accel.getY();
		zOffset = accel.getZ();
		
		xOffset = -xOffset;
		yOffset = -yOffset;
		zOffset = -zOffset;
	}
	
}

