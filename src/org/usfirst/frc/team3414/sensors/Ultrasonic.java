package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.robot.RobotStatus;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;


public class Ultrasonic extends Thread implements IMeasureDistance {

	public final double VOLTS_PER_CM = .00488281;
	private final AnalogInput analogInput;
	private SerialPort serialPort;
	private int sampleRate;
	private double[] samples; 
	int sampleIndex = 0;
	private double distance;
	
	
	private Ultrasonic(SerialPort serial, AnalogInput analog, int samplesPerSecond, int sampleSize)
	{
		samples = new double[sampleSize];
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
	
	protected Ultrasonic(SerialPort input, int sampleSize) {
		this(input, null, 10, sampleSize);
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
				samples[sampleIndex++] = cm;
				distance = cm;
				sampleIndex %= samples.length;
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
	
	protected Ultrasonic(AnalogInput input, int sampleSize) {
		this(null, input, 10, sampleSize);

	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public double getCm() {
		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;
		double sum = 0;
		for (int inx = 0; inx < samples.length; inx++)
		{
			double value = samples[inx];
			if (value > maxValue)
			{
				maxValue = value;
			}
			if (value < minValue)
			{
				minValue = value;
			}
			sum += value;
		}
		sum -= maxValue + minValue;
		distance = sum/(samples.length - 2);
		return distance;
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

