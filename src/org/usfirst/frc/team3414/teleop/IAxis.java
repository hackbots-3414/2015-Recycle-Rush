package org.usfirst.frc.team3414.teleop;


public interface IAxis
{		
	public double getVerticalAxis();
	
	public double getHorizontalAxis();
	
	public double getRotationalAxis();
	
	public double getGamepadVerticalAxisOne();
	
	public double getGamepadHorizontalAxisOne();
	
	public double getGamepadVerticalAxisTwo();
	
	public double getGamepadHorizontalAxisTwo();
		
	public double getDirectionDegrees();
	
	public double getMagnitude();
	
	public double getTwist();
}

