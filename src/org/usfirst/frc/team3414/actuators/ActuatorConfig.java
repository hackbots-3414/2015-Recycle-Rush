package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.SensorConfig;

import edu.wpi.first.wpilibj.CANTalon;

public class ActuatorConfig
{
	private static ActuatorConfig singleton; 
	
	private static final int LIFTER_GRIP = 0;
	private static final int LIFT_MOTOR = 5;
	private static final int MOTOR_LEFT_FRONT = 1;
	private static final int MOTOR_LEFT_REAR = 2;
	private static final int MOTOR_RIGHT_FRONT = 3;
	private static final int MOTOR_RIGHT_REAR = 4;
	private IDriveTrain driveTrain;
	private ILiftAssist forklift;
	private IEncodedMotor motor;
	private IServo servo;
	private CANTalon leftFront;
	private CANTalon rightFront;
	private CANTalon leftRear;
	private CANTalon rightRear;
	
	private ActuatorConfig()
	{
		SensorConfig sensors = SensorConfig.getInstance();
		leftFront = new CANTalon(MOTOR_LEFT_FRONT);
		rightFront = new CANTalon(MOTOR_RIGHT_FRONT);
		leftRear = new CANTalon(MOTOR_LEFT_REAR);
		rightRear = new CANTalon(MOTOR_RIGHT_REAR);
		driveTrain = new MecanumDrive(sensors.getClock(), sensors.getAccelerometer(), sensors.getGyro(), leftFront, rightFront, leftRear, rightRear);
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