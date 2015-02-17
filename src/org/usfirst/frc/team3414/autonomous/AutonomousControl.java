package org.usfirst.frc.team3414.autonomous;
import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.IDetectLines;
import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;

/**
 * Controls which autonomous procedure will run based on the autonomous switches at the time of running
 * 
 * @author Ray
 *
 */
public class AutonomousControl
{	
	public Camera cameraAssist = new Camera();

	public ISwitch iAutonomousSwitches;
	
	public IDriverAssist iDriverAssist;
	
	public IDetectObjects iDetectObjects;
	
	public IDetectLines iDetectLines;
	
	public ILiftAssist iLiftAssist;
	
	public ITimeEventHandler timeEventHandler;
	
	public IDriveTrain mecanumDrive;
	
	private Forklift forklift;
	
	private AutonomousProcedure driveIntoAuto; // Creates all of the autonomouses that will be used.
	private AutonomousProcedure oneYellowToteAuto;
	private AutonomousProcedure twoYellowToteAuto;
	private AutonomousProcedure threeYellowToteAuto;
	private AutonomousProcedure oneRecycleBinAuto;
	private AutonomousProcedure twoRecycleBinAuto;
	private AutonomousProcedure driveBackwardIntoAuto;
	
	public AutonomousControl(AutonnomousConfig config) 
	{
		super();
		iAutonomousSwitches = config.getiAutonomousSwitches();
		iDriverAssist = config.getiDriverAssist();
		iDetectObjects = config.getiDetectObjects();
		iDetectLines = config.getiDetectLines();
		iLiftAssist = config.getiLiftAssist();
		mecanumDrive = config.getMecanumDrive();
		forklift = config.getForklift();
		
		driveIntoAuto = new DriveIntoAuto(mecanumDrive, timeEventHandler); // Creates all of the autonomouses that will be used.
		driveBackwardIntoAuto = new DriveBackwardIntoAuto(mecanumDrive, timeEventHandler);
		oneYellowToteAuto = new OneYellowToteAuto(iDriverAssist, driveBackwardIntoAuto, forklift, mecanumDrive, timeEventHandler);
		twoYellowToteAuto = new TwoYellowToteAuto(iDriverAssist, driveBackwardIntoAuto, cameraAssist, forklift, mecanumDrive, timeEventHandler);
		threeYellowToteAuto = new ThreeYellowToteAuto(iDriverAssist, forklift, mecanumDrive, timeEventHandler);
		oneRecycleBinAuto = new OneRecycleBinAuto(iDriverAssist, driveBackwardIntoAuto, forklift, mecanumDrive, timeEventHandler);
		twoRecycleBinAuto = new TwoRecycleBinAuto(iDriverAssist, driveBackwardIntoAuto, forklift, mecanumDrive, timeEventHandler);
	}

	/**
	 * Calls the autonomous selected by the inputed AutonomousType, usually from the counter switch on the robot.
	 * 
	 * @param AutonomousType
	 */
	public void doAuto(AutonomousMode AutonomousType) 
	{
		// If nothing is passed in then we will just not do anything
		if(AutonomousType == null)
		{
			return;
		}
		
		// Each autonomous if loop calls it's respective autonomous procedure
		if(AutonomousType == AutonomousMode.DRIVEINTOAUTO)
		{
			driveIntoAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.NOTHING)
		{
			return;
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE1)
		{
			oneYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE2)
		{
			twoYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.YELLOWTOTE3)
		{
			threeYellowToteAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.RECYCLECAN1)
		{
			oneRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.RECYCLECAN2)
		{
			twoRecycleBinAuto.doAuto();
		}
		
		if(AutonomousType == AutonomousMode.GREYTOTES)
		{
			// TODO: implement grey tote autonomous
		}
	}
}

