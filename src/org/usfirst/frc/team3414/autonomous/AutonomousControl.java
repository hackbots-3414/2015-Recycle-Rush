package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.SensorConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls which autonomous procedure will run based on the autonomous switches
 * at the time of running
 * 
 * @author Ray
 *
 */
public class AutonomousControl
{// Creates all of the autonomouses that will be used.
//	private AutonomousProcedure driveIntoAuto; 
//	private AutonomousProcedure oneYellowToteAuto;
//	private AutonomousProcedure twoYellowToteAuto;
//	private AutonomousProcedure threeYellowToteAuto;
	private AutonomousProcedure oneRecycleBinAuto;
//	private AutonomousProcedure twoRecycleBinAuto;

	ISwitch switches;

	public AutonomousControl()
	{

//		switches = SensorConfig.getInstance().getAutoModeSelectSwitch();

		//driveIntoAuto = new DriveIntoAuto(); // Creates all of the autonomouses that will be used.
//		oneYellowToteAuto = new OneYellowToteAuto();
//		twoYellowToteAuto = new TwoYellowToteAuto();
//		threeYellowToteAuto = new ThreeYellowToteAuto();
		oneRecycleBinAuto = new OneRecycleBinAuto();
//		twoRecycleBinAuto = new TwoRecycleBinAuto();
		SmartDashboard.putBoolean("Prep Bin 1", false);
	}

	/**
	 * Calls the autonomous selected by the inputed AutonomousType, usually from
	 * the counter switch on the robot.
	 * 
	 * @param AutonomousType
	 */
	public void doAuto()
	{
//		SwitchPositions AutonomousType = switches.get();

		// Each autonomous if loop calls it's respective autonomous procedure
//		switch (AutonomousType)
//		{
//		case DRIVEFORWARD:
//			driveIntoAuto.doAuto();
//			break;
//		case NOTHING:
//			break;
//		case YELLOWTOTE1:
//			oneYellowToteAuto.doAuto();
//			break;
//		case YELLOWTOTE2:
//			twoYellowToteAuto.doAuto();
//			break;
//		case YELLOWTOTE3:
//			threeYellowToteAuto.doAuto();
//			break;
//		case RECYCLECAN1:
//			SmartDashboard.putBoolean("Prep Bin 1", true);
			oneRecycleBinAuto.doAuto();
//			break;
//		case RECYCLECAN2:
//			twoRecycleBinAuto.doAuto();
//			break;
//		case GREYTOTES:
//			// TODO: Implement grey tote auto
//			break;
//		}
	}
}
