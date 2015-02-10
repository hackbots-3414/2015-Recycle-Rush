package org.usfirst.frc.team3414.sensors;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class LIDAR implements IMeasureDistance
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public LIDAR(){
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getInches() {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addListener(IMeasureDistanceListener listener, long distance) {
		// TODO implement me
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getCm() {
		// TODO implement me
		return 0L;	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void removeListener(long distanceEventID) {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public long getFeet() {
		// TODO implement me
		return 0L;	
	}

	@Override
	public double getDistanceCm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDistanceFt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDistanceIn() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

