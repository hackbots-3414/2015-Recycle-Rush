package org.usfirst.frc.team3414.sensors;
import main.java.model.autonomous.SwitchPositions;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * 
 * The class responsible for controlling limit switches on digital IO ports. 
 * 
 * @generated
 */

public class LimitSwitch implements ISwitch
{
	DigitalInput limitSwitch;
	
	/**
	 * 
	 * A constructor for the limit switch. It's arguments are the digital IO port that it's connected to on the RoboRIO.
	 * 
	 * @generated
	 */
	public LimitSwitch(int channel){
		limitSwitch = new DigitalInput(channel);
	}

	/**
	 * 
	 * Gets the position of the limit switch, weather it's depressed (ON) or released (OFF).
	 * 
	 * @generated
	 * @ordered
	 */
	
	public SwitchPositions getPosition() {
		if (limitSwitch.get())
		{
			return SwitchPositions.ON;
		} else
		{
			return SwitchPositions.OFF;
		}
	}
	
}

