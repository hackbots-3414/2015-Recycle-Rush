package org.usfirst.frc.team3414.sensors;

import com.kauailabs.navx_mxp.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

public class NavXMXP implements IMeasureDirection, IMeasureAcceleration
{
    private static NavXMXP singleton = null;
    private AHRS imu = null;

    	private NavXMXP() 
    	{
    	    imu = new AHRS(new SerialPort(57600, SerialPort.Port.kMXP));
    	}
	public static synchronized NavXMXP getInstance()
	{

		if (singleton == null)
		{
			singleton = new NavXMXP();
		}

		return singleton;
	}
	@Override
	public double getAccelX()
	{
	    return imu.getWorldLinearAccelX();
	}
	@Override
	public double getAccelY()
	{
	    return imu.getWorldLinearAccelY();
	}
	@Override
	public double getAccelZ()
	{
	    return imu.getWorldLinearAccelZ();
	}
	@Override
	public void reset()
	{
	    imu.resetDisplacement();
	}
	@Override
	public double getChangeInDegreesPerSecond()
	{
	    return 0;
	}
	@Override
	public double getDegrees()
	{
	    return imu.getCompassHeading();
	}
	@Override
	public double getRadians()
	{
	    return Math.toRadians(imu.getCompassHeading());
	}
}
