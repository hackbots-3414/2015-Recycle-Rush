package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.LimitSwitch;

/**
 * 
 * Implements the autonomous switches which determine what autonomous mode we will start the robot in. The switch can be toggled from one to eight.
 * 
 * @author Ray
 *
 */
public class AutonomousSwitches implements ISwitch {
	LimitSwitch one;
	LimitSwitch two;
	LimitSwitch four;

	public AutonomousSwitches(int channelOnes, int channelTwos, int channelFours) {
		one = new LimitSwitch(channelOnes, false);
		two = new LimitSwitch(channelTwos, false);
		four = new LimitSwitch(channelFours, false);
	}

	/**
	 * 
	 *  The switch positon return types with corresponding numbers are as follows.
	 *  0: Nothing
	 *  1: Drive Forward
	 *  2: One Tote
	 *  3: Two Totes
	 *  4: Three Totes
	 *  5: One Can
	 *  6: Two Cans
	 *  7: Grey Totes
	 * 
	 * @generated
	 * @ordered
	 */

	public SwitchPositions get() 
	{
		if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.OFF)
		{
			return SwitchPositions.NOTHING;
		}
		
		if(one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.OFF)
		{
			return SwitchPositions.DRIVEFORWARD;
		}
		
		if(one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.OFF)
		{
			return SwitchPositions.YELLOWTOTE1;
		}
		
		if(one.get() == SwitchPositions.ON && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.OFF)
		{
			return SwitchPositions.YELLOWTOTE2;
		}
		
		if(one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.ON)
		{
			return SwitchPositions.YELLOWTOTE3;
		}
		
		if(one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.ON)
		{
			return SwitchPositions.RECYCLECAN1;
		}
		
		if(one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.ON)
		{
			return SwitchPositions.RECYCLECAN2;
		}
		
		if(one.get() == SwitchPositions.ON && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.ON)
		{
			return SwitchPositions.GREYTOTES;
		}
		return SwitchPositions.DRIVEFORWARD;
	}
	
}
