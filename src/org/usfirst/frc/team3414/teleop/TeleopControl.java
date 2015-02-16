package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.actuators.*;
import org.usfirst.frc.team3414.sensors.IClock;
import org.usfirst.frc.team3414.sensors.ITimeEventHandler;
import org.usfirst.frc.team3414.sensors.VirtualClock;

public class TeleopControl
{
	private Controller driverControl;
	private MecanumDrive robot;
	private Forklift lifter;

	private boolean restrictButtonFive = false;
	private boolean restrictButtonSix = false;
	private boolean restrictButtonSeven = false;
	private boolean restrictButtonEight = false;
	private boolean restrictButtonNine = false;
	private boolean restrictButtonTen = false;
	
	public TeleopControl()
	{
		driverControl = new Controller();
		lifter = Forklift.createInstance();
		lifter.start();
		lifter.goToBottomLimit();
		robot = MecanumDrive.createInstance(new VirtualClock());
	}

	//private double setFork = 0;
	
	public void runTeleop()
	{
		/*
		//FORKLIFT GRAVITY TEST
		if (driverControl.getJoy().getButtonFive() && !restrictButtonFive)
		{
			restrictButtonFive = true;
			setFork += 0.5;
		}
		if (!driverControl.getJoy().getButtonFive() && restrictButtonFive)
		{
			restrictButtonFive = false;
		}
		
		if (driverControl.getJoy().getButtonSix() && !restrictButtonSix)
		{
			restrictButtonSix = true;
			setFork -= 0.5;
		}
		if (!driverControl.getJoy().getButtonSix() && restrictButtonSix)
		{
			restrictButtonSix = false;
		}
		lifter.setSpeed(setFork);
		*/
		
		robot.move(driverControl.getJoy().getMagnitude(), driverControl.getJoy().getDirectionDegrees(), driverControl.getJoy().getTwist());
		
		//BUTTON FIVE
		if (driverControl.getJoy().getButtonFive() && !restrictButtonFive)
		{
			restrictButtonFive = true;
			lifter.goToGround();
		}
		if (!driverControl.getJoy().getButtonFive() && restrictButtonFive)
		{
			restrictButtonFive = false;
		}

		//BUTTON SIX
		if (driverControl.getJoy().getButtonSix() && !restrictButtonSix)
		{
			restrictButtonSix = true;
			lifter.goToBottomLimit();
		}
		if (!driverControl.getJoy().getButtonSix() && restrictButtonSix)
		{
			restrictButtonSix = false;
		}

		//BUTTON SEVEN
		if (driverControl.getJoy().getButtonSeven() && !restrictButtonSeven)
		{
			restrictButtonSeven = true;
			lifter.previousToteLength();
		}
		if (!driverControl.getJoy().getButtonSeven() && restrictButtonSeven)
		{
			restrictButtonSeven = false;
		}

		//BUTTON EIGHT
		if (driverControl.getJoy().getButtonEight() && !restrictButtonEight)
		{
			restrictButtonEight = true;
			lifter.nextToteLength();
		}
		if (!driverControl.getJoy().getButtonEight() && restrictButtonEight)
		{
			restrictButtonEight = false;
		}

		//BUTTON NINE
		if (driverControl.getJoy().getButtonNine() && !restrictButtonNine)
		{
			restrictButtonNine = true;
			lifter.previousBinLength();
		}
		if (!driverControl.getJoy().getButtonNine() && restrictButtonNine)
		{
			restrictButtonNine = false;
		}

		//BUTTON TEN
		if (driverControl.getJoy().getButtonTen() && !restrictButtonTen)
		{
			restrictButtonTen = true;
			lifter.nextBinLength();
		}
		if (!driverControl.getJoy().getButtonTen() && restrictButtonTen)
		{
			restrictButtonTen = false;
		}
		
	}

}
