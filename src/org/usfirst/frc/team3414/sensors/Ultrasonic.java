package org.usfirst.frc.team3414.sensors;

import edu.wpi.first.wpilibj.AnalogInput;


public class Ultrasonic implements IMeasureDistance {


	private final AnalogInput ultrasonic;

	public Ultrasonic(AnalogInput ultrasonic) {
		this.ultrasonic = ultrasonic;
		ultrasonic.setAverageBits(30);
	}


	public void removeListener(long distanceEventID) {
		// TODO implement me
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public double getCm() {
		return ultrasonic.getAverageVoltage() / .1024;
	}

	/**
	 * Use average voltage to get distance in feet
	 * 
	 * @generated
	 * @ordered
	 */

	public double getFeet() {
		return getCm() * .0328084;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */

	public void addListener(IMeasureDistanceListener listener, long distance) {
		// TODO implement me
	}

	/**
	 * Gets distance in feet and converts to inches
	 * 
	 * @generated
	 * @ordered
	 */

	public double getInches() {
		return getFeet() * 12;
	}


	@Override
	public double getDistanceCm() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getDistanceFt() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getDistanceIn() {
		// TODO Auto-generated method stub
		return 0;
	}

}
