package model.sensors;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import model.teleop.Display;
import model.autonomous.IDetectObjects;
import java.util.HashSet;
import model.autonomous.ObjectDetector;
import model.autonomous.AutonomousControl;
import model.autonomous.Obstacle;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Radar implements IDetectObjects
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Display display;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<ObjectDetector> objectDetector;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public AutonomousControl autonomousControl;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public Radar(){
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

