package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.AnalogInput;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Infrared implements IMeasureDistance
{
	private AnalogInput channel;

	public Infrared(AnalogInput channel) {
		super();
		if(channel != null)
		{
			this.channel = channel;	
		}
		else
		{
			throw new RuntimeException("The Channel should nopt be null");
		}
		
	}
	
	public Infrared(int channelNumber) {
		super();
		channel = new AnalogInput(channelNumber);
	}

	@Override
	public double getCm() {
		double voltage = channel.getVoltage();
		return 61.573 * Math.pow(voltage, -1.1068);
	}

	/**
	 * Use average voltage to get distance in feet
	 * 
	 * @generated
	 * @ordered
	 */

	public double getFeet() {
		return DistanceConversion.cmToInches(getCm());
	}

	/**
	 * Gets distance in feet and converts to inches
	 * 
	 * @generated
	 * @ordered
	 */

	public double getInches() {
		return DistanceConversion.inToFeet(getFeet());
	}
}

