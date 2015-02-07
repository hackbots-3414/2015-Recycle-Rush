package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class EncodedMotor extends Motor implements IEncodedMotor {

	private Encoder encoder;
	
	public EncodedMotor(SpeedController controller, Encoder encoder, boolean reverse) {
		super(controller, reverse);
		this.encoder= encoder;
	}
	
	public EncodedMotor(CANTalon controller, boolean reverse) {
		super(controller, reverse);

	}
	
	/**
	 * Regardless of the current feedback device (if any), this receives the encoder velocity.
	 * @return
	 */
	public double getEncoderVelocity()
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
	
	/**
	 * Regardless of the current feedback device, if any, this receives the current encoder position.
	 * @return
	 */
	public int getEncoderPosition()
	{
		int position = 0;
		if(encoder != null)
		{
			position = encoder.get();
		}
		else if(motorController instanceof CANTalon)
		{
			position = ((CANTalon)motorController).getEncPosition();
		}
		return position;
	}
}
