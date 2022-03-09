// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AimSub extends SubsystemBase {
  private TalonSRX aimMotor;
  Encoder encoder = new Encoder(0, 1);

  /** Creates a new AimSub. */
  public AimSub() {
    aimMotor = new TalonSRX(Constants.AIM_MOTOR);
    aimMotor.configFactoryDefault();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Enc Dist", encoder.getDistance());
  }

  public void rotateAimCL() {
    aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SPEED);

  }

  public void rotateAimCO(){
    aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SPEED);
  }

  public void stopAim(){
    aimMotor.set(ControlMode.PercentOutput, Constants.AIM_STOP);
  }
}