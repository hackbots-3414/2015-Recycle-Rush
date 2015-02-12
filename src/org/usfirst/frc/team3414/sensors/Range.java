package org.usfirst.frc.team3414.sensors;

public class Range 
{
	public double minimum;
	public double maximum;
	
	public Range(double minimum, double maximum) 
	{
		super();
		
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	public double getMinimum() 
	{
		return minimum;
	}
	
	public void setMinimum(double minimum) 
	{
		this.minimum = minimum;
	}
	
	public double getMaximum() 
	{
		return maximum;
	}
	
	public void setMaximum(double maximum) 
	{
		this.maximum = maximum;
	}
}
