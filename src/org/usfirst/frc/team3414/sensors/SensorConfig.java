package org.usfirst.frc.team3414.sensors;

import org.usfirst.frc.team3414.autonomous.AutonomousSwitches;
import org.usfirst.frc.team3414.autonomous.IVision;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI.Port;


public class SensorConfig {
	/* DIGITAL CHANNELS */
	private final int LIFT_ENCODER_A = 3;
	private final int  LIFT_ENCODER_B = 4;
	private final int LIMIT_SWITCH_TOP = 5;
	private final int LIMIT_SWITCH_BOTTOM = 6;
	private final int AUTO_SWITCH_ONES = ;
	private final int AUTO_SWITCH_TWOS = ;
	private final int AUTO_SWITCH_FOURS = ;

	/* ANALOG CHANNELS*/
	private final int ULTRASONIC_REAR = 0;
	private final int ULTRASONIC_RIGHT = 1;
    private final int ULTRASONIC_LEFT = 2;
    /* Port for SPI */
    private Port SPI_PORT = Port.kOnboardCS0;
    private final int TEMP_NUMBER_OF_SENSORS = 5;
    
   	private IClock clock;
	private ILimitSwitch forkLiftTop;
	private ILimitSwitch forkLiftBottom;
	private IDistanceEventHandler distanceEventSystem;
	//private IDetectLines lineDetector;
	private IMeasureAcceleration accelerometer;
	private IMeasureDistance distanceSensorLeft;
	private IMeasureDistance distanceSensorRight;
	private IMeasureDistance distanceSensorRear;
//	private IMeasureDistance sensorBarSensor1;
//	private IMeasureDistance sensorBarSensor2;
//	private IMeasureDistance sensorBarSensor3;
//	private IMeasureDistance sensorBarSensor4;
//	private IMeasureDistance sensorBarSensor5;
	private IPowerEventHandler powerEventSystem;
	private IEncoder forkLiftEncoder;
	private ISensorBar sensorBar;
	private IMeasureDirection gyro;
	private ISwitch autoModeSelectSwitch;
	private IVision visionAssist;
	
	private static SensorConfig singleton;
	
	private SensorConfig()
	{
		super();
		clock = new VirtualClock();
		forkLiftEncoder = new Encoder(LIFT_ENCODER_A, LIFT_ENCODER_B);
		powerEventSystem = new PowerDistributionBoard();
		distanceSensorLeft = new Ultrasonic(new AnalogInput(ULTRASONIC_LEFT));
		distanceSensorRear = new Ultrasonic(new AnalogInput(ULTRASONIC_REAR));
		distanceSensorRight = new Ultrasonic(new AnalogInput(ULTRASONIC_RIGHT));
		distanceEventSystem = new DistanceEventHandler();
		accelerometer = new OurBuiltInAccelerometer();
		forkLiftBottom = new LimitSwitch(LIMIT_SWITCH_BOTTOM, true);
		forkLiftTop = new LimitSwitch(LIMIT_SWITCH_TOP, true);
		sensorBar = new SensorBar(SPI_PORT, TEMP_NUMBER_OF_SENSORS);
		gyro = new Gyroscope(0);
		autoModeSelectSwitch = new AutonomousSwitches(AUTO_SWITCH_ONES, AUTO_SWITCH_TWOS, AUTO_SWITCH_FOURS);
		visionAssist = new Camera();
	}
	
	public static SensorConfig getInstance()
	{
		if(singleton == null)
		{
			singleton = new SensorConfig();
		}
		return singleton;
	}
	
	public ILimitSwitch getForkLiftTop()
	{
		return forkLiftTop;
	}

	public ILimitSwitch getForkLiftBottom()
	{
		return forkLiftBottom;
	}
	public IClock getClock()
	{
		return clock;
	}
	
	public IDistanceEventHandler getDistanceEventSystem()
	{
		return distanceEventSystem;
	}

//	public IDetectLines getLineDetector()
//	{
//		return lineDetector;
//	}

	public IMeasureAcceleration getAccelerometer()
	{
		return accelerometer;
	}

	public IMeasureDistance getDistanceSensorLeft()
	{
		return distanceSensorLeft;
	}

	public IMeasureDistance getDistanceSensorRight()
	{
		return distanceSensorRight;
	}

	public IMeasureDistance getDistanceSensorRear()
	{
		return distanceSensorRear;
	}

	public IPowerEventHandler getPowerEventSystem()
	{
		return powerEventSystem;
	}

	public IEncoder getForkLiftEncoder()
	{
		return forkLiftEncoder;
	}

	public ISensorBar getSensorBar()
	{
		return sensorBar;
	}
	public IMeasureDirection getGyro()
	{
		return gyro;
	}

	public ISwitch getAutoModeSelectSwitch()
	{
		return autoModeSelectSwitch;
	}

	public IVision getVisionAssist()
	{
		return visionAssist;
	}
}