package org.usfirst.frc.team3414.sensors;

public class SensorBar implements IDetectSweetSpot, ITimeListener
{
	ISPI spi;
	
	byte[] returnedBytes;
	byte[] distanceSensorNumber;
	float[] sensorValues;
	int numberOfSensors;
	long timeEventID;
	

	@Override
	public void timeEvent(TimeEventArgs timeEvent) {
		if(timeEvent.getTimeEventID() == timeEventID)
		{
			int val = 0;
		
			spi.transaction(distanceSensorNumber, returnedBytes, numberOfSensors*4);
		
			for(int i = 0; i <= numberOfSensors; i++)
			{
				for(int j = i*4, k = 0; k < 4; k++, j++)
				{
					val += returnedBytes[j] << (k*8);
				}
			
				sensorValues[i] = (float)val;
				val = 0;
			}
		}
	}
	
	double slopeA = slope(sensorValues[0], sensorValues[1], 9.5);
	double slopeB = slope(sensorValues[1], sensorValues[2], 17.9);
	double slopeC = slope(sensorValues[2], sensorValues[3], 17.9);
	double slopeD = slope(sensorValues[3], sensorValues[4], 9.5);

	protected SensorBar(edu.wpi.first.wpilibj.SPI.Port arduinoPort, int tempNumberOfSensors)
	{
		timeEventID = SensorConfig.getInstance().getClock().addListener(this, 1000); // Updates the values every second
		
		numberOfSensors = tempNumberOfSensors;
		
		spi = new SPI(arduinoPort);
		
	    spi.setClockRate(1000000);
		spi.setMSBFirst();
		spi.setChipSelectActiveLow();
		
		
		distanceSensorNumber = new byte[numberOfSensors];
	    returnedBytes = new byte[numberOfSensors*4];
	    sensorValues = new float[numberOfSensors];
	    
		for(int i = 0; i < numberOfSensors; i++)
		{
			distanceSensorNumber[i] = (byte) i;
	    }
	}
	
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
