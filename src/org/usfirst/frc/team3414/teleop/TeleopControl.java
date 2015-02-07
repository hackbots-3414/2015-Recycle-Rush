package org.usfirst.frc.team3414.teleop;
import org.usfirst.frc.team3414.actuators.*;

public class TeleopControl
{
	private Controller driverControl;
	MecanumDrive robot;
	ForkLift lifter;
	
	public TeleopControl(){
		driverControl = new Controller();	
		lifter = ForkLift.createInstance();
		robot = MecanumDrive.createInstance();
	}
	
	public void runTeleop(IDriveTrain s) {
		driverControl.getJoy().getVerticalAxis();
		//drive.move(driverControl.getJoy());
		if(driverControl.getJoy().getButtonFive()) {
			lifter.goToTop();
		}
		if(driverControl.getJoy().getButtonSix()) {
			lifter.goToBottom();
		}
		if(driverControl.getJoy().getButtonSeven()) {
			lifter.nextToteLength();
		}
		if(driverControl.getJoy().getButtonEight()) {
			lifter.nextBinLength();
		}
		if(driverControl.getJoy().getButtonNine()) {
			lifter.previousToteLength();
		}
		if(driverControl.getJoy().getButtonTen()) {
			lifter.previousBinLength();
		}
		if(driverControl.getJoy().getButtonEleven()) {
			lifter.stop();
		}
		if(driverControl.getJoy().getButtonTwelve()) {
			lifter.zeroEncoder();
		}
	}
}
