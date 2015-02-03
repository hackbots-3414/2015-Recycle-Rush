package org.usfirst.frc.team3414.teleop;
import org.usfirst.frc.team3414.actuators.*;

public class TeleopControl
{
	Controller driverControl;
	
	public TeleopControl(){
		driverControl = new Controller();
	}
	
	public void runTeleop(IDriveTrain s) {
		driverControl.getJoy().getVerticalAxis();
		//drive.move(driverControl.getJoy());
                IDriveTrain a = s;
                
	}

}
