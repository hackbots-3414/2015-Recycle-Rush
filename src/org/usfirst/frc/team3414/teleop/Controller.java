package org.usfirst.frc.team3414.teleop;

public class Controller
{	
	MyJoystick joystick;
	//MyJoystick gamepad;
	
	public Controller()
	{
		
		joystick = new MyJoystick(1);
		//gamepad = new MyJoystick(2);
	}
	
	public MyJoystick getJoy() {
		return joystick;
	}
	/*
	public MyJoystick getGame() {
		return gamepad;
	}
        */
	
	
}

