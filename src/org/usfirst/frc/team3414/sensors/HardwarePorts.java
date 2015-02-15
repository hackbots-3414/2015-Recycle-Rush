package org.usfirst.frc.team3414.sensors;

public interface HardwarePorts
{
	// Servos
	int LIFTER_GRIP = 0;
	
    // Motors
    int RIGHT_FRONT_DRIVE;
    int LEFT_FRONT_DRIVE;
    int RIGHT_BACK_DRIVE;
    int LEFT_BACK_DRIVE;
    int LIFT_MOTOR = 5;
    
    // DigitalIOs
    int LIFT_ENCODER_A;
    int LIFT_ENCODER_B;
    int ULTRASONIC_LEFT;
    int ULTRASONIC_RIGHT;
    int ULTRASONIC_BACK;
    int INFRARED_LEFT;
    int INFRARED_RIGHT;
    int INFRARED_BACK;
    int LIMIT_SWITCH_TOP;
    int LIMIT_SWITCH_BOTTOM;
    
    // Analogs
    int ACCELEROMETER;
    int GYRO;
    int COMPASS;
    
    // Joystick
    int JOYSTICK = 1;
}
