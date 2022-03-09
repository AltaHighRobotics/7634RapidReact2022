// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;

public class IntakeSub extends SubsystemBase {
  /** Creates a new IntakeSub. */
  private VictorSPX intakeMotor;

  public IntakeSub() {
    intakeMotor = new VictorSPX(Constants.INTAKE_MOTOR);
    intakeMotor.configFactoryDefault();
    intakeMotor.setInverted(true);
  }

  public void startMotor() {
    intakeMotor.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
  }

  public void stopMotor() {
    intakeMotor.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
