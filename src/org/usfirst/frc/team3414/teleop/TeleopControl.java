package org.usfirst.frc.team3414.teleop;
import org.usfirst.frc.team3414.actuators.*;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;

public class TeleopControl
{
	private Controller driverControl;
	MecanumDrive robot;
	
	public TeleopControl(){
		driverControl = new Controller();	
		
		robot = MecanumDrive.createInstance();
	}
	
	public void runTeleop(IDriveTrain s) {
		driverControl.getJoy().getVerticalAxis();
		//drive.move(driverControl.getJoy());
               
                
	}
}
