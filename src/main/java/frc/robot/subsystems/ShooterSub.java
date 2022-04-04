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
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

public class ShooterSub extends SubsystemBase {
  private WPI_TalonFX shooterMotor;

  /** Creates a new ShooterSub. */
  public ShooterSub() {
    shooterMotor = new WPI_TalonFX(Constants.SHOOTER_MOTOR);

    shooterMotor.configFactoryDefault();
    shooterMotor.setNeutralMode(NeutralMode.Coast);
    shooterMotor.setInverted(TalonFXInvertType.CounterClockwise);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void stopShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput, Constants.STOP_SHOOTER);
    SmartDashboard.putBoolean("Shooter on", false);
  }

  public void startShooterMotor() {
    //shooterMotor.set(ControlMode.PercentOutput ,Constants.START_SHOOTER);
    shooterMotor.setVoltage(12);
    SmartDashboard.putBoolean("Shooter on", true);
  }

  public void revShooterMotor() {
    shooterMotor.set(ControlMode.PercentOutput ,-Constants.INTAKE_REV_SPEED);
    SmartDashboard.putBoolean("Shooter on", true);
  }
}