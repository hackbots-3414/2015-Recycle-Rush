package org.usfirst.frc.team3414.teleop;

public class Controller
{	
	Logitech3DProJoystick joystick;
	//MyJoystick gamepad;
	
	public Controller(int joystickChannel)
	{
		
		joystick = new Logitech3DProJoystick(joystickChannel);
		//gamepad = new MyJoystick(2);
	}
	
	public IJoystick getJoystick() {
		return joystick;
	}
	/*
	public MyJoystick getGame() {
		return gamepad;
	}
        */
	
	
}

