package org.usfirst.frc.team3414.actuators;
import main.java.model.autonomous.SwitchPositions;
import main.java.model.sensors.ISwitch;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ForkLift implements ILiftAssist
{
	IMotor liftMotor;
	ICanMotor canMotor;
	double speedSetpoint;
	
	ISwitch topSwitch;
	ISwitch bottomSwitch;
	
	long encoderPosition;
	
	public ForkLift(ICanMotor tempCanMotor, IMotor tempLiftMotor, ISwitch tempTopSwitch, ISwitch tempBottomSwitch, int forkLiftPort, FeedbackDevice device){
		tempCanMotor = canMotor;
		tempLiftMotor = liftMotor;
		tempTopSwitch = topSwitch;
		tempBottomSwitch = bottomSwitch;
		
		
		
		//Sets the Kp, Ki and Kd constants.
		canMotor.setPID(1, 0, 0);
		
		//Speed setpoint for the closed loop system.
		speedSetpoint = 5;
	}

	public ForkLift(ICanMotor tempCanMotor, IMotor tempLiftMotor, int forkLiftPort){
		tempLiftMotor = liftMotor;
		tempCanMotor = canMotor;
		
		//Speed setpoint for the closed loop system.
		speedSetpoint = 1.0;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void stop() {
		/*double rampRate = (liftMotor.getSpeed()/10);
		
		for(int i = 0; i < 10; i++)
		{
			liftMotor.forward(liftMotor.getSpeed()-(rampRate*i));
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
			
		}
		liftMotor.set(0.0);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void goToTop() {
		liftMotor.set(speedSetpoint);
		
		
		while(topSwitch.getPosition() == SwitchPositions.OFF)
		{
			
		}
		
		stop();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void previousToteLength() {
		liftMotor.set(-speedSetpoint);
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void nextToteLength() {
		// TODO implement me	
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void goToBottom() {
		liftMotor.set(-speedSetpoint);
		
		
		while(topSwitch.getPosition() == SwitchPositions.OFF)
		{
			
		}
		
		stop();
	}
	
	public void previousBinLength()
	{
		
	}
	
	public void nextBinLength()
	{
		
	}
	
}

