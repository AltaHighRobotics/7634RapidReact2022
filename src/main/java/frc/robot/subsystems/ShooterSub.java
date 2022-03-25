// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class ShooterSub extends SubsystemBase {
  private TalonFX shooterMotor;

  /** Creates a new ShooterSub. */
  public ShooterSub() {
    shooterMotor = new TalonFX(Constants.SHOOTER_MOTOR);

    shooterMotor.configFactoryDefault();
    shooterMotor.setInverted(TalonFXInvertType.CounterClockwise);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput, Constants.STOP_SHOOTER);
  }

  public void startShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput ,Constants.START_SHOOTER);
  }

  public void revShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput ,-Constants.INTAKE_REV_SPEED);
  }
}