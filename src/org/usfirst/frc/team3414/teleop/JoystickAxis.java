package org.usfirst.frc.team3414.teleop;

public enum JoystickAxis
{
    HORIZONTAL_AXIS(0),
	VERTICAL_AXIS(1),
	TWIST_AXIS(3),
	THROTTLE_AXIS(4);
	
	private int value;

    private JoystickAxis(int value) {
            this.value = value;
    }
    
    public int getValue()
	{
		return value;
	}
}
