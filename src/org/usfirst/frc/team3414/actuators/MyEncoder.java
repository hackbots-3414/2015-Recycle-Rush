package org.usfirst.frc.team3414.actuators;
import edu.wpi.first.wpilibj.Encoder;

public class MyEncoder
{
	private MyEncoder encode;
	
	public MyEncoder(MyEncoder _FiveEncoders)
	{
		this.encode = _FiveEncoders;
		
	}
	
	public int getRaw()
	{
		return encode.getRaw();
	}
	
	public int get()
	{
		return encode.get();
	}
	
	
	public int getCount(){
		return(encode.getCount());
	}
	
	
	
	public boolean getDirection()
	{
		return encode.getDirection();
	}
	
	public double getDistance()
	{
		return encode.getDistance();
	}
	
	public void setDistancePerPulse(double distancePerPulse)
	{
		encode.setDistancePerPulse(distancePerPulse);
	}
	
	public int getEncodingScale()
	{
		return encode.getEncodingScale();
	}
	
	public double getPeriodic()
	{
		return encode.getPeriodic();
	}
	
	public double getRate()
	{
		return encode.getRate();
	}

	public boolean getStopped()
	{
		return encode.getStopped();
	}
	public double getPeriod()
	{
		return encode.getPeriod();
	}
	
	public void reset()
	{
		encode.reset();
	}
}

