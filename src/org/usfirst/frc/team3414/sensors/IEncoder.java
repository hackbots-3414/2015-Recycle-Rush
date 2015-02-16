package org.usfirst.frc.team3414.sensors;

public interface IEncoder {
	public int getPosition();

	public boolean getDirection();

	public double getDistance();

	public void setDistancePerPulse(double distancePerPulse);

	public double getRate();

	public boolean getStopped();

	public void reset();
}
