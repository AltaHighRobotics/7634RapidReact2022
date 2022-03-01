// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;

public class FeederSub extends SubsystemBase {
  /** Creates a new FeederSub. */
  private final VictorSPX feederMotor;

  public FeederSub() {
    feederMotor = new VictorSPX(Constants.FEEDER_MOTOR);
  }

  public void startMotor() {
    feederMotor.set(ControlMode.PercentOutput, Constants.FEEDER_SPEED);
  }

  public void stopMotor() {
    feederMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public void startMotorRev() {
    feederMotor.set(ControlMode.PercentOutput, -Constants.FEEDER_SPEED_REV);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
