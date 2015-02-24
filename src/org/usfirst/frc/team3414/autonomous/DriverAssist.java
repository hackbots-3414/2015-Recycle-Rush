package org.usfirst.frc.team3414.autonomous;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.IDriveTrain;
import org.usfirst.frc.team3414.sensors.IMeasureDistance;
import org.usfirst.frc.team3414.sensors.ISensorBar;
import org.usfirst.frc.team3414.sensors.SensorConfig;
import org.usfirst.frc.team3414.sensors.SweetSpotMode;
import org.usfirst.frc.team3414.sensors.SweetSpotState;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class DriverAssist implements IDriverAssist
{
    public IMeasureDistance iMeasureDistance;

    public IDriveTrain drive;
    public ActuatorConfig actuator;
    public AutonomousControl autonomousControl;
    public SensorConfig sensor;
    public ISensorBar sensorBar;

    public DriverAssist()
    {
	sensorBar = sensor.getSensorBar();
	drive = actuator.getDriveTrain();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */

    public void correctRotation(SweetSpotMode mode)
    {
	while (sensorBar.getSweetSpotState(mode) != SweetSpotState.SWEET_SPOT)
	{
	    if (sensorBar.getSweetSpotState(mode) == SweetSpotState.TURN_CLOCKWISE)
	    {
		drive.move(0, 0, .75);
	    } else if (sensorBar.getSweetSpotState(mode) == SweetSpotState.TURN_COUNTER_CLOCKWISE)
	    {
		drive.move(0, 0, -.75);
	    }
	}
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */

    public void binSweetSpot(SweetSpotMode mode)
    {
	while (sensorBar.getSweetSpotState(mode) != SweetSpotState.SWEET_SPOT)
	{
	    if (sensorBar.getSweetSpotState(mode) == SweetSpotState.MOVE_LEFT)
	    {
		drive.move(.75, -90, 0);
	    } else if (sensorBar.getSweetSpotState(mode) == SweetSpotState.MOVE_RIGHT)
	    {
		drive.move(.75, 90, 0);
	    }
	}
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */

    public void toteSweetSpot(SweetSpotMode mode)
    {
	while (sensorBar.getSweetSpotState(mode) != SweetSpotState.SWEET_SPOT)
	{
	    if (sensorBar.getSweetSpotState(mode) == SweetSpotState.MOVE_LEFT)
	    {
		drive.move(.75, -90, 0);
	    } else if (sensorBar.getSweetSpotState(mode) == SweetSpotState.MOVE_RIGHT)
	    {
		drive.move(.75, 90, 0);
	    }
	}
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */

    public void avoidSideDebris()
    {
	// BIG BOY WILL NOT
	// WORK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
