package org.usfirst.frc.team3414.sensors;

public interface HardwarePorts
{
	// Servos
	int LIFTER_GRIP = 0;
	
    // Motors
    int RIGHT_FRONT_DRIVE = 2;
    int LEFT_FRONT_DRIVE = 4;
    int RIGHT_BACK_DRIVE = 3;
    int LEFT_BACK_DRIVE = 1;
    int LIFT_MOTOR = 5;
    
    // DigitalIOs
    int LIFT_ENCODER_A = 3;
    int LIFT_ENCODER_B = 4;
    int ULTRASONIC_LEFT = 2;
    int ULTRASONIC_RIGHT = 1;
    int ULTRASONIC_BACK = 0;
    int LIMIT_SWITCH_TOP = 5;
    int LIMIT_SWITCH_BOTTOM = 6;
    
    // Analogs
    //int ACCELEROMETER;
    int GYRO_A = 0;
    int GYRO_B = 1;
    //int COMPASS = 0;
    
    // Joystick
    int JOYSTICK = 1;
}
