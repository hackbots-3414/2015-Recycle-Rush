package org.usfirst.frc.team3414.sensors;

public class SensorBar implements IDetectSweetSpot
{
	
	//SPI spi;
	
	//byte[] currentByte;
	//byte[] distanceSensorNumber;
	private IMeasureDistance[] sensors;
	private SweetSpotMode mode;
	
	protected SensorBar(IMeasureDistance sensor0, IMeasureDistance sensor1,IMeasureDistance sensor2, IMeasureDistance sensor3,IMeasureDistance sensor4)
	{
		sensors = new IMeasureDistance[5];
		//Check if any sensor is null //throw exception
		sensors[0] = sensor0;
		sensors[1] = sensor1;
		sensors[2] = sensor2;
		sensors[3] = sensor3;
		sensors[4] = sensor4;
		for(IMeasureDistance sensor: sensors)
		{
			
		}
		
	}
//	public SensorBar(Port port, int sensorBarSize)
//	{
//		spi  = new SPI(port);
//		spi.setClockRate(1000000);
//		spi.setMSBFirst();
//		spi.setChipSelectActiveLow();
//	
//		distanceSensorNumber = new byte[sensorBarSize];
//		currentByte = new byte[sensorBarSize*4];
//		
//		for(int i = 0; i < sensorBarSize; i++)
//		{
//			distanceSensorNumber[i] = (byte) i;
//		}
//	}

	@Override
	public SweetSpotState getSweetSpotState(SweetSpotMode mode)
	{
		SweetSpotState state = SweetSpotState.NONE;
		switch (mode)
		{
		case TOTE_THIN:
			state = getSweetSpotStateThin();
			break;
		case TOTE_WIDE:
		default:
			state = getSweetSpotStateWide();
			break;
		}
	}

	private SweetSpotState getSweetSpotStateWide()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private SweetSpotState getSweetSpotStateThin()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

