package org.usfirst.frc.team3414.autonomous;

import java.util.List;


/**
 * Implemented by classes which detect objects on the field
 * 
 * @author Ray
 *
 */
public  interface IDetectObjects 
{
	/**
	 * Returns a list of objects detected by the object detecting sensor
	 * 
	 * @return
	 */
	public List<Obstacle> getObjects() ;
	
	
}

