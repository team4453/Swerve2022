// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Robot;
import static frc.robot.utils.Constants.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.XboxController;

public class Shooter {
    private Robot _robot;

    XboxController operator = new XboxController(1);
    double currentTime;

    private final CANSparkMax leftShooterMotor;
    private final CANSparkMax rightShooterMotor;

    public Shooter(Robot robot)
    {
        _robot = robot;
        leftShooterMotor = new CANSparkMax(SHOOTER_LEFT_MOTOR, MotorType.kBrushless);
        rightShooterMotor = new CANSparkMax(SHOOTER_RIGHT_MOTOR, MotorType.kBrushless);
    }

    /** Configuration */
    double lowSpeed = .25;
    double highSpeed = .70;
    boolean mode = false;

    /** This function is called periodically during operator control. */
    public void teleopPeriodic() {
        // Check input from right trigger
        if (operator.getRightTriggerAxis() > 0.2) {
            runShooter();
        }
        else {
            stop();
        }

        if (operator.getRightBumper()) {
            mode = true;
        }
        else {
            mode = false;
        }
    }

    public void runShooter() {
        if (mode) {
            // Run shooter at high speed
            leftShooterMotor.set(_robot.invert(highSpeed));
            rightShooterMotor.set(highSpeed);
        }
        else {
            // Run shooter at low speed
            leftShooterMotor.set(_robot.invert(lowSpeed));
            rightShooterMotor.set(lowSpeed);
        }
    }

    public void forceRunShooter() {
        leftShooterMotor.set(_robot.invert(highSpeed));
        rightShooterMotor.set(highSpeed);
    }

    public void stop() {
        leftShooterMotor.set(0);
        rightShooterMotor.set(0);
    }
}
