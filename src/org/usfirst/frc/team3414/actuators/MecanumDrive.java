package org.usfirst.frc.team3414.actuators;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import main.java.model.sensors.IMeasureDirection;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class MecanumDrive implements IDriveTrain
{	
	RobotDrive drive;
	
	public MecanumDrive(IMeasureDirection tempDirectionSensor, SpeedController frontLeftChannel, SpeedController frontRightChannel, SpeedController backLeftChannel, SpeedController backRightChannel){
		drive = new RobotDrive(frontLeftChannel, backLeftChannel, frontRightChannel, backRightChannel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void move(double velocity, double angle, double rotation) {
		drive.mecanumDrive_Polar(velocity, angle, rotation);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void rotateToDegrees(double degrees) {
		drive.mecanumDrive_Polar(0, degrees, 0);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void stop() {
		drive.mecanumDrive_Polar(0.0, 0, 0);
	}
}

