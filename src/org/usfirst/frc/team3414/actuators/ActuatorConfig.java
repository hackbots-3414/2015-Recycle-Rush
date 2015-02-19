package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.SensorConfig;

import edu.wpi.first.wpilibj.CANTalon;

public class ActuatorConfig
{
	private static ActuatorConfig singleton; 
	
	private static final int LIFTER_GRIP = 0;
	private static final int LIFT_MOTOR = 5;
	private IDriveTrain driveTrain;
	private ILiftAssist forklift;
	private IEncodedMotor motor;
	private IServo servo;
	
	private ActuatorConfig()
	{
		SensorConfig sensors = SensorConfig.getInstance();
		driveTrain = new MecanumDrive(sensors.getClock(), sensors.getAccelerometer(), sensors.getGyro());
		motor = new EncodedMotor(new CANTalon(LIFT_MOTOR), sensors.getForkLiftEncoder());
		servo = new Servo(LIFTER_GRIP);
		forklift = new Forklift(motor, sensors.getForkLiftTop(), sensors.getForkLiftBottom(), servo);
	}
	
	public static ActuatorConfig getInstance()
	{
		if(singleton == null)
		{
			singleton = new ActuatorConfig();
		}
		return singleton;
	}
	
	public IDriveTrain getDriveTrain()
	{
		return driveTrain;
	}

	public ILiftAssist getForklift()
	{
		return forklift;
	}

	public IServo getServo()
	{
		return servo;
	}
}