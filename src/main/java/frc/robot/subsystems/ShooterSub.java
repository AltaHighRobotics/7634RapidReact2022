// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @Hacker
  @New Hawks
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import java.lang.Math;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.DemandType;
import frc.robot.util.ConfigurablePID;

public class ShooterSub extends SubsystemBase {
  private WPI_TalonFX shooterMotor;
  private ConfigurablePID shooterPid;

  private double pidValue;
  private boolean motorPidOn;
  private double motorVel;

  /** Creates a new ShooterSub. */
  public ShooterSub() {
    shooterMotor = new WPI_TalonFX(Constants.SHOOTER_MOTOR);

    shooterMotor.configFactoryDefault();
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    shooterMotor.setInverted(TalonFXInvertType.CounterClockwise);

    shooterPid = new ConfigurablePID(
      Constants.SHOOTER_PORPORTIONAL_GAIN,
      Constants.SHOOTER_INTERGRAL_GAIN,
      Constants.SHOOTER_DERIVITIVE_GAIN,
      Constants.SHOOTER_MAX_PROPORTIONAL,
      Constants.SHOOTER_MAX_INTEGRAL,
      Constants.SHOOTER_MAX_DERIVITIVE,
      Constants.SHOOTER_POWER_OFFSET,
      1,
      1
    );
    
    pidValue = 0.0;
    motorPidOn = false;
    motorVel = 0.0;

    shooterMotor.setSelectedSensorPosition(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (motorPidOn) {
      pidLoop();
    }

    motorVel = shooterMotor.getSelectedSensorVelocity();
    SmartDashboard.putNumber("Shooter velocity", motorVel);
  }

  private void pidLoop() {
    motorVel = shooterMotor.getSelectedSensorVelocity();
    pidValue = shooterPid.runPID(Constants.SHOOTER_VELOCITY, motorVel);

    shooterMotor.set(ControlMode.PercentOutput, pidValue);

    SmartDashboard.putNumber("Shooter power", pidValue);
    SmartDashboard.putNumber("Shooter velocity", motorVel);
    SmartDashboard.putNumber("Shooter pid error", motorVel - Constants.SHOOTER_VELOCITY);
  }

  public double getMotorVelocity() {
    return shooterMotor.getSelectedSensorVelocity() / Constants.UNITS_PER_ROTATION;
  }

  public void stopShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput, Constants.STOP_SHOOTER);
    motorPidOn = false;
    SmartDashboard.putBoolean("Shooter on", false);
  }

  public void startShooterMotor() {
    //shooterMotor.set(ControlMode.PercentOutput, Constants.START_SHOOTER);
    motorPidOn = true;
    SmartDashboard.putBoolean("Shooter on", true);
  }

  public void revShooterMotor() {
    motorPidOn = false; // To stop pid loop from running and messing with this function.
    shooterMotor.set(ControlMode.PercentOutput, -Constants.INTAKE_REV_SPEED);
    SmartDashboard.putBoolean("Shooter on", true);
  }

  public boolean pidLoopOn() {
    return motorPidOn;
  }
}