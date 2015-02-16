package org.usfirst.frc.team3414.sensors;

import java.util.Collections;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Ultrasonic extends Thread implements IMeasureDistance {

	public final double VOLTS_PER_CM = .00488281;
	private final AnalogInput analogInput;
	private SerialPort serialPort;
	private int sampleRate;
	private double[] samples = new double[10];
	int index = 0;
	private double distance;
	
	private Ultrasonic(SerialPort serial, AnalogInput analog, int samplesPerSecond)
	{
		this.analogInput = analog;
		this.serialPort = serial;
		if(samplesPerSecond <=0 )
		{
			this.sampleRate = 100;
		}
		else
		{
			this.sampleRate = 1000/samplesPerSecond;
		}
		for(int i =0; i< samples.length; i++){samples[i] = 0;}
		start();
	}
	
	public Ultrasonic(SerialPort input) {
		this(input, null, 10);
	}
	
	@Override 
	public void run() {

		while(RobotStatus.isRunning()){
			if (serialPort!= null){
				//serialPort.readString();
				//Run lOw pass filter
			}
			
			else if (analogInput!= null){
				double voltage = analogInput.getVoltage();
				double cm = voltage / VOLTS_PER_CM;
				//Run low pass filter
				samples[index] = cm;
				index = (index + 1) % 10;
			}
			else
			{
				distance = Double.NaN;
			}
			try {
				Thread.sleep(sampleRate);
			} catch (InterruptedException e) {
				
			}
		}
 
	}
	
	public Ultrasonic(AnalogInput input) {
		this(null, input, 10);

	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public double getCm() {
		//return StdStats.mean(samples);
		//return distance;
		return 0;
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

