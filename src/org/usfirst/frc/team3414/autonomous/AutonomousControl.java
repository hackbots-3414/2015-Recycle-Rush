package org.usfirst.frc.team3414.autonomous;
import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.IDetectAutoZone;
import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.SensorConfig;

/**
 * Controls which autonomous procedure will run based on the autonomous switches at the time of running
 * 
 * @author Ray
 *
 */
public class AutonomousControl
{	
	private AutonomousProcedure driveIntoAuto; // Creates all of the autonomouses that will be used.
	private AutonomousProcedure oneYellowToteAuto;
	private AutonomousProcedure twoYellowToteAuto;
	private AutonomousProcedure threeYellowToteAuto;
	private AutonomousProcedure oneRecycleBinAuto;
	private AutonomousProcedure twoRecycleBinAuto;
	private AutonomousProcedure driveBackwardIntoAuto;
	
	ISwitch switches;
	
	public AutonomousControl() 
	{
		super();
		
		SensorConfig sensors = SensorConfig.getInstance();
		ActuatorConfig actuators = ActuatorConfig.getInstance();
		AutonomousConfig auton = AutonomousConfig.getInstance();
		 
		switches = sensors.getAutoModeSelectSwitch();
		
		
		driveIntoAuto = new DriveIntoAuto(); // Creates all of the autonomouses that will be used.
		driveBackwardIntoAuto = new DriveBackwardIntoAuto();
		oneYellowToteAuto = new OneYellowToteAuto();
		twoYellowToteAuto = new TwoYellowToteAuto();
		threeYellowToteAuto = new ThreeYellowToteAuto();
		oneRecycleBinAuto = new OneRecycleBinAuto();
		twoRecycleBinAuto = new TwoRecycleBinAuto();
	}

	/**
	 * Calls the autonomous selected by the inputed AutonomousType, usually from the counter switch on the robot.
	 * 
	 * @param AutonomousType
	 */
	public void doAuto() 
	{
		SwitchPositions AutonomousType = switches.get();
		
		// If nothing is passed in then we will just not do anything
		if(AutonomousType == null)
		{
			return;
		}
		
		// Each autonomous if loop calls it's respective autonomous procedure
		if(AutonomousType == SwitchPositions.DRIVEFORWARD)
		{
			driveIntoAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.NOTHING)
		{
			return;
		}
		
		if(AutonomousType == SwitchPositions.YELLOWTOTE1)
		{
			oneYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.YELLOWTOTE2)
		{
			twoYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.YELLOWTOTE3)
		{
			threeYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.RECYCLECAN1)
		{
			oneRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.RECYCLECAN2)
		{
			twoRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == SwitchPositions.GREYTOTES)
		{
			// TODO: implement grey tote autonomous
		}
	}
}

