// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;

public class FeederSub extends SubsystemBase {
  /** Creates a new FeederSub. */
  private final WPI_VictorSPX feederMotor;

  public FeederSub() {
    feederMotor = new WPI_VictorSPX(Constants.FEEDER_MOTOR);
    feederMotor.configFactoryDefault();
    feederMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void startMotor() {
    feederMotor.set(ControlMode.PercentOutput, Constants.FEEDER_SPEED * Constants.FEEDER_INVERT);
  }

  public void revMotor() {
    feederMotor.set(ControlMode.PercentOutput, -Constants.FEEDER_REV_SPEED * Constants.FEEDER_INVERT);
  }

  public void stopMotor() {
    feederMotor.neutralOutput();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
