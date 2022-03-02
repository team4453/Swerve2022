// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
// import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

import static frc.robot.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Lifter {

    private final Robot robot;
    Constants constants = new Constants();

    private final CANSparkMax leftLiftMotor;
    private final CANSparkMax rightLiftMotor;

    public Lifter()
    {
        robot = new Robot();
        leftLiftMotor = new CANSparkMax(LIFT_LEFT_MOTOR, MotorType.kBrushless);
        rightLiftMotor = new CANSparkMax(LIFT_RIGHT_MOTOR, MotorType.kBrushless);
    }
    
    //private final Joystick operator = new Joystick(OPERATOR_JOYSTICK_ID);

    /**  Configuration */
    private double liftModifier = 0.5;
    double input = constants.operatorLY() * liftModifier;

    /** This function is called periodically during operator control. */
    public void teleopPeriodic() {

        // Check input from Left Y axis
        if (constants.operatorLY() > 0.1) {

            leftLiftMotor.set(input);
            rightLiftMotor.set(input);
        }
    }
}
