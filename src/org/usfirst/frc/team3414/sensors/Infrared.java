package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Infrared implements IMeasureDistance
{
	private AnalogInput channel;

	protected Infrared(AnalogInput channel) {
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
	
	protected Infrared(int channelNumber) {
		this(new AnalogInput(channelNumber));
	}

	@Override
	public double getCm() {
		double voltage = channel.getVoltage();
		SmartDashboard.putNumber("Voltage", voltage);
		SmartDashboard.putNumber("CM", 61.573 * Math.pow(voltage, -1.1068));
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

