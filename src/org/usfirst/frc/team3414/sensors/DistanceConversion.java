package org.usfirst.frc.team3414.sensors;

public class DistanceConversion {
	
	public static final float  CM_TO_INCHES = 0.393f;
	public static final byte  INCHES_TO_FEET = 12;
	
	public static double cmToInches(double cm)
	{
		return cm * CM_TO_INCHES;
	}

	public static double inToFeet(double inch)
	{
		return inch * INCHES_TO_FEET;
	}
}
