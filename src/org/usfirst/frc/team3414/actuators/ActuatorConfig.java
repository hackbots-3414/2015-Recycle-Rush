package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.SensorConfig;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class ActuatorConfig
{
	private static ActuatorConfig singleton = null;

	private static final int LIFTER_GRIP = 9;
	private static final int LIFT_MOTOR = 5;
	private static final int MOTOR_LEFT_FRONT = 1;
	private static final int MOTOR_LEFT_REAR = 2;
	private static final int MOTOR_RIGHT_FRONT = 3;
	private static final int MOTOR_RIGHT_REAR = 4;
	private static final int LED_PORT = 8;
	
	private IDriveTrain driveTrain;
	private ILiftAssist forklift;
	private IEncodedMotor motor;
	private IServo servo;
	private CANTalon leftFront;
	private CANTalon rightFront;
	private CANTalon leftRear;
	private CANTalon rightRear;
	private Talon ledTalon;
	private SpeedController leftFrontInvert;
	private SpeedController leftRearInvert;
	
	private ActuatorConfig()
	{
		try
		{
			SensorConfig sensors = SensorConfig.getInstance();
			leftFront = new CANTalon(MOTOR_LEFT_FRONT);
			rightFront = new CANTalon(MOTOR_RIGHT_FRONT);
			leftRear = new CANTalon(MOTOR_LEFT_REAR);
			rightRear = new CANTalon(MOTOR_RIGHT_REAR);
			ledTalon = new Talon(LED_PORT);
			
			leftFrontInvert = new InverseController(leftFront);
			leftRearInvert = new InverseController(leftRear);
			
			driveTrain = new MecanumDrive(sensors.getClock(), sensors.getAccelerometer(), sensors.getGyro(), leftFront, leftRear, rightFront,
					rightRear);
			motor = new EncodedMotor(new CANTalon(LIFT_MOTOR), sensors.getForkLiftEncoder());
			servo = new Servo(LIFTER_GRIP);
			forklift = new Forklift(motor, sensors.getForkLiftTop(), sensors.getForkLiftBottom(), servo, sensors.getClock());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static synchronized ActuatorConfig getInstance()
	{
		if (singleton == null)
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