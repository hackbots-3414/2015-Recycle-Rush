package org.usfirst.frc.team3414.sensors;

import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;

public class SensorBar implements IMeasureDistance
{

	SPI spi;
	
	byte[] currentByte;
	byte[] distanceSensorNumber;
	
	public SensorBar(Port port, int sensorBarSize)
	{
		spi  = new SPI(port);
		spi.setClockRate(1000000);
		spi.setMSBFirst();
		spi.setChipSelectActiveLow();
	
		distanceSensorNumber = new byte[sensorBarSize];
		currentByte = new byte[sensorBarSize*4];
		
		for(int i = 0; i < sensorBarSize; i++)
		{
			distanceSensorNumber[i] = (byte) i;
		}
	}
	
	@Override
	public double getCm() 
	{
		//spi.transaction(distanceSensorNumber, currentByte, 4);
		return 0.0;
		
	}

	@Override
	public double getFeet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getInches() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

