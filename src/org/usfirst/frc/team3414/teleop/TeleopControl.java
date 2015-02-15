package org.usfirst.frc.team3414.teleop;

import org.usfirst.frc.team3414.actuators.*;

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
		robot = MecanumDrive.createInstance();
	}

	private double setFork = 0;
	
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
		

		/*
		//BUTTON FIVE
		if (driverControl.getJoy().getButtonFive() && !restrictButtonFive)
		{
			restrictButtonFive = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonFive() && restrictButtonFive)
		{
			restrictButtonFive = false;
		}

		//BUTTON SIX
		if (driverControl.getJoy().getButtonSix() && !restrictButtonSix)
		{
			restrictButtonSix = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonSix() && restrictButtonSix)
		{
			restrictButtonSix = false;
		}

		//BUTTON SEVEN
		if (driverControl.getJoy().getButtonSeven() && !restrictButtonSeven)
		{
			restrictButtonSeven = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonSeven() && restrictButtonSeven)
		{
			restrictButtonSeven = false;
		}

		//BUTTON EIGHT
		if (driverControl.getJoy().getButtonEight() && !restrictButtonEight)
		{
			restrictButtonEight = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonEight() && restrictButtonEight)
		{
			restrictButtonEight = false;
		}

		//BUTTON NINE
		if (driverControl.getJoy().getButtonNine() && !restrictButtonNine)
		{
			restrictButtonNine = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonNine() && restrictButtonNine)
		{
			restrictButtonNine = false;
		}

		//BUTTON TEN
		if (driverControl.getJoy().getButtonTen() && !restrictButtonTen)
		{
			restrictButtonTen = true;
			//TODO: Lifter Action
		}
		if (!driverControl.getJoy().getButtonTen() && restrictButtonTen)
		{
			restrictButtonTen = false;
		}
		*/
	}

}
