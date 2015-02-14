package org.usfirst.frc.team3414.sensors;

public interface HardwarePorts
{
	// Solenoids
	int LIFTER_GRIP;
	
    // Motors
    int RIGHT_FRONT_DRIVE;
    int LEFT_FRONT_DRIVE;
    int RIGHT_BACK_DRIVE;
    int LEFT_BACK_DRIVE;
    int LIFT_MOTOR;
    
    // DigitalIOs
    int LIFT_ENCODER_A;
    int LIFT_ENCODER_B;
    int ULTRASONIC_PORT;
    int ULTRASONIC_STARBOARD;
    int ULTRASONIC_STERN;
    int INFRARED_PORT;
    int INFRARED_STARBOARD;
    int INFRARED_STERN;
    int LIMIT_SWITCH_TOP;
    int LIMIT_SWITCH_BOTTOM;
    
    // Analogs
    int ACCELEROMETER;
    int GYRO;
    
    // Joysticks
    int JOYSTICK = 1;
}
