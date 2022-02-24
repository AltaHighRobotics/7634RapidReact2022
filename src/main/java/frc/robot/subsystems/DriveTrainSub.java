// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSub extends SubsystemBase {
  /** Creates a new DriveTrainSub. */
  // I know am i werid.  I name stuff right to left.  Is that so bad?
  private VictorSPX rightDrive1;
  private VictorSPX rightDrive2;
  private VictorSPX leftDrive1;
  private VictorSPX leftDrive2;

  private AHRS navX;

  public DriveTrainSub() {
    rightDrive1 = new VictorSPX(Constants.RIGHT_DRIVE_1);
    rightDrive2 = new VictorSPX(Constants.RIGHT_DRIVE_2);
    rightDrive2.follow(rightDrive1);

    leftDrive1 = new VictorSPX(Constants.LEFT_DRIVE_1);
    leftDrive2 = new VictorSPX(Constants.LEFT_DRIVE_2);
    leftDrive2.follow(leftDrive1);

    navX = new AHRS(I2C.Port.kMXP, Constants.NAVX_UPDATE_RATE);
    navX.reset();
    navX.resetDisplacement();
    navX.calibrate();
    navX.zeroYaw();
  }

  public void setRightMotors(double speed) {
    rightDrive1.set(ControlMode.PercentOutput, speed * Constants.DRIVE_SPEED);
  }

  public void setLeftMotors(double speed) {
    leftDrive1.set(ControlMode.PercentOutput, speed * Constants.DRIVE_SPEED);
  }

  public void setMotors(double speed) {
    setRightMotors(speed);
    setLeftMotors(speed);
  }

  public AHRS setNavx() {
    return navX;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
