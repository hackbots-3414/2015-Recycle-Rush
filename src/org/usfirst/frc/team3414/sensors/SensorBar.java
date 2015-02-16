package org.usfirst.frc.team3414.sensors;

public class SensorBar implements IDetectSweetSpot
{

	// SPI spi;

	// byte[] currentByte;
	// byte[] distanceSensorNumber;
	private IMeasureDistance[] sensors;
	

	double slopeA = slope(sensors[0].getCm(), sensors[1].getCm(), 9.5);
	double slopeB = slope(sensors[1].getCm(), sensors[2].getCm(), 17.9);
	double slopeC = slope(sensors[2].getCm(), sensors[3].getCm(), 17.9);
	double slopeD = slope(sensors[3].getCm(), sensors[4].getCm(), 9.5);

	protected SensorBar(IMeasureDistance sensor0, IMeasureDistance sensor1, IMeasureDistance sensor2, IMeasureDistance sensor3,
			IMeasureDistance sensor4)
	{
		sensors = new IMeasureDistance[5];
		// Check if any sensor is null //throw exception
		sensors[0] = sensor0;
		sensors[1] = sensor1;
		sensors[2] = sensor2;
		sensors[3] = sensor3;
		sensors[4] = sensor4;
		for (IMeasureDistance sensor : sensors)
		{
			if (sensor == null)
			{
				throw new RuntimeException("It is not OK to have a null Distance Sensot");
			}
		}
		
		

	}

	// public SensorBar(Port port, int sensorBarSize)
	// {
	// spi = new SPI(port);
	// spi.setClockRate(1000000);
	// spi.setMSBFirst();
	// spi.setChipSelectActiveLow();
	//
	// distanceSensorNumber = new byte[sensorBarSize];
	// currentByte = new byte[sensorBarSize*4];
	//
	// for(int i = 0; i < sensorBarSize; i++)
	// {
	// distanceSensorNumber[i] = (byte) i;
	// }
	// }

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
		return state;
	}

	private static final double VARIABILITY = 0.5;

	private SweetSpotState getSweetSpotStateWide()
	{
		SweetSpotState state = SweetSpotState.NONE;
		if ((Math.abs(slopeA - slopeB) <= VARIABILITY) && (Math.abs(slopeB - slopeC) <= VARIABILITY) && (Math.abs(slopeC - slopeD) <= VARIABILITY))
		{
			if (slopeD < -VARIABILITY)
			{
				state = SweetSpotState.TURN_CLOCKWISE;
			}
			if (slopeD > VARIABILITY)
			{
				state = SweetSpotState.TURN_COUNTER_CLOCKWISE;
			}
			if (Math.abs(slopeD) <= VARIABILITY)
			{
				state = SweetSpotState.SWEET_SPOT;
			}
		} else if ((Math.abs(slopeA - slopeB) <= VARIABILITY) && (Math.abs(slopeB - slopeC) <= VARIABILITY)
				&& (Math.abs(slopeC - slopeD) > VARIABILITY))
		{
			state = SweetSpotState.MOVE_LEFT;
		} else if ((Math.abs(slopeA - slopeB) > VARIABILITY) && (Math.abs(slopeB - slopeC) <= VARIABILITY)
				&& (Math.abs(slopeC - slopeD) <= VARIABILITY))
		{
			state = SweetSpotState.MOVE_RIGHT;
		}
		return state;

	}

	private double slope(double a, double b, double c)
	{
		return (a - b) / c;
	}

	private SweetSpotState getSweetSpotStateThin()
	{
		SweetSpotState state = SweetSpotState.NONE;
		if ((Math.abs(slopeA - slopeB) > VARIABILITY) && (Math.abs(slopeB - slopeC) <= VARIABILITY) && (Math.abs(slopeC - slopeD) > VARIABILITY))
		{
			if (slopeB < -VARIABILITY)
			{
				state = SweetSpotState.TURN_CLOCKWISE;
			}
			if (slopeB > VARIABILITY)
			{
				state = SweetSpotState.TURN_COUNTER_CLOCKWISE;
			}
			if (Math.abs(slopeB) <= VARIABILITY)
			{
				state = SweetSpotState.SWEET_SPOT;
			}
		}
		// TODO Auto-generated method stub
		return state;
	}

}
