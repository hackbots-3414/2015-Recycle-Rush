package org.usfirst.frc.team3414.actuators;

public interface ICanMotor extends IMotor {
	/**
	 * Sets the Proportional, Integral and Derivative constants of the PID closed loop system.
	 * @param Kp
	 * @param Ki
	 * @param Kd
	 */
	public void setPID(double Kp, double Ki, double Kd);
	
	/**
	 * Sets the proportional constant.
	 * @param Kp
	 */
	public void setP(double Kp);
	
	/**
	 * Sets the integral constant.
	 * @param Ki
	 */
	public void setI(double Ki);
	
	/**
	 * Sets the derivative constant.
	 * @param Kd
	 */
	public void setD(double Kd);
	
	/**
	 * Gets the proportional constant
	 * @return
	 */
	public double getP();
	
	/**
	 * Gets the integral constant
	 * @return
	 */
	public double getI();
	
	/**
	 * Gets the derivative constant.
	 * @return
	 */
	public double getD();

}
