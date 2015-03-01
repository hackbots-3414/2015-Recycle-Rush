package org.usfirst.frc.team3414.teleop;

public enum DPadDirection
{
	UP(1),
	DOWN(2),
	LEFT(3),
	RIGHT(4);
	
	private int value;

    private DPadDirection(int value) {
            this.value = value;
    }
    
    public int getValue()
	{
		return value;
	}
}
