package org.usfirst.frc.team3414.autonomous;


/**
 * 
 * Describes an obstacle that is picked up during vision processing
 * 
 * @author Ray
 *
 */
public class Obstacle
{
	/**
	 * The side the detected object is on, if the object is a tote.
	 */
	public ToteSide side;

	/**
	 * The color of the detected object
	 */
	public ObjectColor color;
	

	/**
	 * The type of object detected by vision
	 */
	public ObjectType type;
	

	/**
	 * The distance away the object is in, expressed in feet
	 */
	public long distanceInFt;
	
	/**
	 * The distance away the object is in, expressed in centimeters
	 */
	public long distanceInCm;
	

	/**
	 * Constructs the obstacle object
	 */
	public Obstacle(){
		super();
	}

}

