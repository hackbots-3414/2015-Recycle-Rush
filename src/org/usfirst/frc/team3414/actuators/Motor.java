package org.usfirst.frc.team3414.actuators;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class Motor implements IMotor {
	/**
	 * The CAN Talon base object.
	 * 
	 * @generated
	 * @ordered
	 */

	public SpeedController motorController;

	private final boolean inverseDirection;

	public Motor(SpeedController motor, boolean inverseDirection) {
		this.motorController = motor;
		this.inverseDirection = inverseDirection;
	}

	public void stop() {
		motorController.set(0.0);
	}

	public void backward(double speed) {
		double actualSpeed = inverseDirection ? -Math.abs(speed) : Math.abs(speed);
		motorController.set(-actualSpeed);
	}

	public void forward(double speed, int motorStep) {
		double increaseIncrement = speed / motorStep;

		for (int currentStep = 1; currentStep <= motorStep + 1; currentStep++) {
			double nextSpeed = increaseIncrement * currentStep;

			if (inverseDirection) {
				motorController.set(-nextSpeed);
			} else {
				motorController.set(nextSpeed);
			}
		}
	}

	public void forward(double speed) {
		double actualSpeed = inverseDirection ? -Math.abs(speed) : Math.abs(speed);
		motorController.set(actualSpeed);
	}

	public void backward(double speed, int motorStep) {
		double increment = Math.abs(speed) / motorStep;

		for (int currentStep = 1; currentStep <= motorStep + 1; currentStep++) {
			double nextSpeed = increment * currentStep;

			forward(nextSpeed);
		}
	}

	public double getSpeed() {
		return inverseDirection ? -motorController.get() : motorController
				.get();
	}
}
