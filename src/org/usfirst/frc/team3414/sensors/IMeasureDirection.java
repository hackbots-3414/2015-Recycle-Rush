package org.usfirst.frc.team3414.sensors;


/**
 * implemented by Gyroscope.java
 * used by MechnumDrive.java
 *
 */
public  interface IMeasureDirection 
{
	
	public double getChangeInDegreesPerSecond() ;
	
	public double getDegrees() ;
	
	public double getRadians() ;
	
	
}

