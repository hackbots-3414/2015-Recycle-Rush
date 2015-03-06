package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.sensors.IDetectAutoZone;

public interface IVision extends IDetectObjects, IDetectAutoZone
{
	public void startAutomaticCapture(String cameraName);
}
