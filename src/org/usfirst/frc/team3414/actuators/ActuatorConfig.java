package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.SensorConfig;

import edu.wpi.first.wpilibj.CANTalon;

public class ActuatorConfig
{
	private static final int LIFTER_GRIP = 0;
	private static final int LIFT_MOTOR = 5;
	private IDriveTrain driveTrain;
	private ILiftAssist forklift;
	private IEncodedMotor motor;
	private IServo servo;
	
	private ActuatorConfig(SensorConfig sensors)
	{
		driveTrain = new MecanumDrive(sensors.getClock(), sensors.getAccelerometer());
		motor = new EncodedMotor(new CANTalon(LIFT_MOTOR), sensors.getForkLiftEncoder());
		servo = new Servo(LIFTER_GRIP);
		forklift = new Forklift(motor, sensors.getForkLiftTop(), sensors.getForkLiftBottom(), servo);
	}
	
	public IDriveTrain getDriveTrain()
	{
		return driveTrain;
	}

	public ILiftAssist getForklift()
	{
		return forklift;
	}
}
=======
package org.usfirst.frc.team3414.actuators;

import org.usfirst.frc.team3414.sensors.SensorConfig;

public class ActuatorConfig
{
	private static final int LIFTER_GRIP = 0;
	private IDriveTrain driveTrain;
	private ILiftAssist forklift;
	private IEncodedMotor motor;
	private IServo servo;
	
	private ActuatorConfig(SensorConfig sensors)
	{
		driveTrain = new MecanumDrive(sensors.getClock(), sensors.getAccelerometer());
		motor = new EncodedMotor(sensors.getForkLiftEncoder());
		servo = new Servo(LIFTER_GRIP);
		forklift = new Forklift(motor, sensors.getForkLiftTop(), sensors.getForkLiftBottom(), servo);
	}
	
	public IDriveTrain getDriveTrain()
	{
		return driveTrain;
	}

	public ILiftAssist getForklift()
	{
		return forklift;
	}
}
