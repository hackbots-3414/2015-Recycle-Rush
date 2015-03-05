package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.MyAutoLimitSwitch;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * Implements the autonomous switches which determine what autonomous mode we
 * will start the robot in. The switch can be toggled from one to eight.
 * 
 * @author Ray
 *
 */
public class AutonomousSwitches implements ISwitch
{
	MyAutoLimitSwitch one;
	MyAutoLimitSwitch two;
	MyAutoLimitSwitch four;

	public AutonomousSwitches(int channelOnes, int channelTwos, int channelFours)
	{
		one = new MyAutoLimitSwitch(channelOnes, false);
		two = new MyAutoLimitSwitch(channelTwos, false);
		four = new MyAutoLimitSwitch(channelFours, false);
		SmartDashboard.putBoolean("BIN 1", false);
		SmartDashboard.putBoolean("Something Else", false);
	}

	/**
	 * 
	 * The switch position return types with corresponding numbers are as
	 * follows. 0: Nothing 1: Drive Forward 2: One Tote 3: Two Totes 4: Three
	 * Totes 5: One Can 6: Two Cans 7: Grey Totes
	 * 
	 * @generated
	 * @ordered
	 */

	public SwitchPositions get()
	{
		// int switch_val = 0;

		// if (one.get()==SwitchPositions.ON)
		// switch_val = 1;
		// if (two.get()==SwitchPositions.ON)
		// switch_val += 2;
		// if (four.get()==SwitchPositions.ON)
		// switch_val += 4;
		//
		// switch (switch_val)
		// {
		// case 0:
		// break;
		// case 1:
		// return SwitchPositions.NOTHING;
		// break;
		//
		// }
		if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.OFF)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.NOTHING;
		} else if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.OFF)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.DRIVEFORWARD;
		} else if (one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.OFF)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.YELLOWTOTE1;
		} else

		if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.OFF)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.YELLOWTOTE2;
		} else

		if (one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.ON)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.YELLOWTOTE3;
		} else

		if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.OFF && four.get() == SwitchPositions.ON)
		{
			SmartDashboard.putBoolean("BIN 1", true);
			return SwitchPositions.RECYCLECAN1;
		} else

		if (one.get() == SwitchPositions.OFF && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.ON)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.RECYCLECAN2;
		} else

		if (one.get() == SwitchPositions.ON && two.get() == SwitchPositions.ON && four.get() == SwitchPositions.ON)
		{
			SmartDashboard.putBoolean("Something Else", true);
			return SwitchPositions.GREYTOTES;
		} else
		{
			SmartDashboard.putBoolean("Something Else", true);

			return SwitchPositions.DRIVEFORWARD;
		}
	}

}
