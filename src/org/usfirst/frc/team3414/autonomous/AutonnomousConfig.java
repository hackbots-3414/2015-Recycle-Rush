package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.Forklift;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.actuators.ILiftAssist;
import org.usfirst.frc.team3414.sensors.Camera;
import org.usfirst.frc.team3414.sensors.IDetectLines;
import org.usfirst.frc.team3414.sensors.ISwitch;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;

public class AutonnomousConfig 
{
	private Camera cameraAssist = new Camera();

	private ISwitch iAutonomousSwitches;
	
	private IDriverAssist iDriverAssist;
	
	private IDetectObjects iDetectObjects;
	
	private IDetectLines iDetectLines;
	
	private ILiftAssist iLiftAssist;
	
	private ITimeEventHandler handler;
	
	private IDriveTrain mecanumDrive;
	
	private Forklift forklift;

	public Forklift getForklift() 
	{
		return forklift;
	}

	public void setForklift(Forklift forklift) 
	{
		this.forklift = forklift;
	}

	public ITimeEventHandler getHandler() 
	{
		return handler;
	}

	public void setHandler(ITimeEventHandler handler) 
	{
		this.handler = handler;
	}

	public IDriveTrain getMecanumDrive() 
	{
		return mecanumDrive;
	}

	public void setMecanumDrive(IDriveTrain mecanumDrive) 
	{
		this.mecanumDrive = mecanumDrive;
	}

	public Camera getCameraAssist() 
	{
		return cameraAssist;
	}

	public void setCameraAssist(Camera cameraAssist) 
	{
		this.cameraAssist = cameraAssist;
	}

	public ISwitch getiAutonomousSwitches()
	{
		return iAutonomousSwitches;
	}

	public void setiAutonomousSwitches(ISwitch iAutonomousSwitches)
	{
		this.iAutonomousSwitches = iAutonomousSwitches;
	}

	public IDriverAssist getiDriverAssist() 
	{
		return iDriverAssist;
	}

	public void setiDriverAssist(IDriverAssist iDriverAssist) 
	{
		this.iDriverAssist = iDriverAssist;
	}

	public IDetectObjects getiDetectObjects()
	{
		return iDetectObjects;
	}

	public void setiDetectObjects(IDetectObjects iDetectObjects)
	{
		this.iDetectObjects = iDetectObjects;
	}

	public IDetectLines getiDetectLines()
	{
		return iDetectLines;
	}

	public void setiDetectLines(IDetectLines iDetectLines) 
	{
		this.iDetectLines = iDetectLines;
	}

	public ILiftAssist getiLiftAssist() 
	{
		return iLiftAssist;
	}

	public void setiLiftAssist(ILiftAssist iLiftAssist) 
	{
		this.iLiftAssist = iLiftAssist;
	}		
}
