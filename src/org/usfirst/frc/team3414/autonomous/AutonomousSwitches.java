package org.usfirst.frc.team3414.autonomous;

import main.java.model.sensors.ISwitch;
import main.java.model.sensors.LimitSwitch;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class AutonomousSwitches implements ISwitch {
	LimitSwitch one;
	LimitSwitch two;
	LimitSwitch four;

	public AutonomousSwitches(int channelOnes, int channelTwos, int channelFours) {
		one = new LimitSwitch(channelOnes);
		two = new LimitSwitch(channelTwos);
		four = new LimitSwitch(channelFours);
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

	@Override
	public SwitchPositions getPosition() 
	{
		if (one.getPosition() == SwitchPositions.OFF && two.getPosition() == SwitchPositions.OFF && four.getPosition() == SwitchPositions.OFF)
		{
			return SwitchPositions.NOTHING;
		}
		
		if(one.getPosition() == SwitchPositions.ON && two.getPosition() == SwitchPositions.OFF && four.getPosition() == SwitchPositions.OFF)
		{
			return SwitchPositions.DRIVEFORWARD;
		}
		
		if(one.getPosition() == SwitchPositions.OFF && two.getPosition() == SwitchPositions.ON && four.getPosition() == SwitchPositions.OFF)
		{
			return SwitchPositions.YELLOWTOTE1;
		}
		
		if(one.getPosition() == SwitchPositions.ON && two.getPosition() == SwitchPositions.ON && four.getPosition() == SwitchPositions.OFF)
		{
			return SwitchPositions.YELLOWTOTE2;
		}
		
		if(one.getPosition() == SwitchPositions.OFF && two.getPosition() == SwitchPositions.OFF && four.getPosition() == SwitchPositions.ON)
		{
			return SwitchPositions.YELLOWTOTE3;
		}
		
		if(one.getPosition() == SwitchPositions.ON && two.getPosition() == SwitchPositions.OFF && four.getPosition() == SwitchPositions.ON)
		{
			return SwitchPositions.RECYCLECAN1;
		}
		
		if(one.getPosition() == SwitchPositions.OFF && two.getPosition() == SwitchPositions.ON && four.getPosition() == SwitchPositions.ON)
		{
			return SwitchPositions.RECYCLECAN2;
		}
		
		if(one.getPosition() == SwitchPositions.ON && two.getPosition() == SwitchPositions.ON && four.getPosition() == SwitchPositions.ON)
		{
			return SwitchPositions.GREYTOTES;
		}
		return SwitchPositions.DRIVEFORWARD;
	
}}
