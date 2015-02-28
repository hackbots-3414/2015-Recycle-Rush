package org.usfirst.frc.team3414.actuators;
import org.usfirst.frc.team3414.sensors.IEncoder;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Used in Forklift.java
 * (NOTE: Is there a problem that both EncodedMotor.java and Encoder.java implement IEncoder.java, such that they both use the same emthods but possible different operations?)
 */
public class EncodedMotor extends Motor implements IEncodedMotor {

	private IEncoder encoder;
	
	public EncodedMotor(SpeedController motor, IEncoder encoder) {
		super(motor, false);
		this.encoder = encoder;
		encoder.setDistancePerPulse(DISTANCE_PER_PULSE);
	}
	
	
	
	public EncodedMotor(CANTalon controller, boolean reverse) {
		super(controller, reverse);
	}

	@Override
	public int getPosition()
	{
		int position = 0;
		if(encoder != null)
		{
			position = encoder.getPosition();
		}
		else if(motorController instanceof CANTalon)
		{
			position = ((CANTalon)motorController).getEncPosition();
		}
		return position;
	}



	@Override
	public boolean getDirection()
	{
		return encoder.getDirection();
	}



	@Override
	public double getDistance()
	{
		return encoder.getDistance();
	}



	@Override
	public void setDistancePerPulse(double distancePerPulse)
	{
		encoder.setDistancePerPulse(distancePerPulse);
		
	}



	@Override
	public double getRate()
	{
		double velocity = Double.NaN;
		if(encoder != null)
		{
			velocity = encoder.getRate();
		}
		else if(motorController instanceof CANTalon)
		{
			velocity = ((CANTalon)motorController).getEncVelocity();
		}
		return velocity;
	}



	@Override
	public boolean getStopped()
	{
		return encoder.getStopped();
	}



	@Override
	public void reset()
	{
		if(encoder != null){
			encoder.reset();
		}
		else if (motorController instanceof CANTalon)
		{
			((CANTalon)motorController).setPosition(0);
		}
		
		
	}
}
