package org.usfirst.frc.team3414.sensors;
import java.util.List;

import org.usfirst.frc.team3414.autonomous.IDetectObjects;
import org.usfirst.frc.team3414.autonomous.Obstacle;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class StereoCamera implements IDetectObjects
{
	public Camera cameraOne;
	public Camera cameraTwo;
	
	public StereoCamera(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public List<Obstacle> getObjects() {
		// TODO implement me
		return null;	
	}
	
}

