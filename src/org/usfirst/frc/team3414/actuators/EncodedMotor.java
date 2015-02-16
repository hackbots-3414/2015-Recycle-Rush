package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.Constants;
import org.usfirst.frc.team3414.sensors.HardwarePorts;
import org.usfirst.frc.team3414.sensors.Encoder;
import org.usfirst.frc.team3414.sensors.IEncoder;

import edu.wpi.first.wpilibj.CANTalon;

public class EncodedMotor extends Motor implements IEncodedMotor, Constants, HardwarePorts {

	private IEncoder encoder;
	
	
	public EncodedMotor(IEncoder encoder) {
		super(new CANTalon(LIFT_MOTOR), NOT_INVERSE);
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
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public double getDistance()
	{
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public void setDistancePerPulse(double distancePerPulse)
	{
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void reset()
	{
		// TODO Auto-generated method stub
		
	}
}
