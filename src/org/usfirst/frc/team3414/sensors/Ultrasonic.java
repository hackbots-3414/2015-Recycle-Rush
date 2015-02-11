package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Ultrasonic implements IMeasureDistance {


	private final AnalogInput analogInput;
	private final PWM pwmInput;
	
	public Ultrasonic(AnalogInput input) {
		this.analogInput = input;
		this.pwmInput = null;
	}
	
	public Ultrasonic(PWM input) {
		this.analogInput = null;
		this.pwmInput = input;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public double getCm() {
		double cm = 0;
		if(analogInput != null)
		{
			SmartDashboard.putNumber("Vcc: ",  analogInput.getValue());
			
			cm = analogInput.getAverageVoltage() / .00245; // 4.9mV per 2 cm
		}
		else if(pwmInput != null)
		{
			//pwmInput.setPeriodMultiplier(mult);
			SmartDashboard.putNumber("PMW Value: ", pwmInput.getRaw());
			//PWM Value
			
		}
		//else if(serialInput != null)
		return cm;
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
