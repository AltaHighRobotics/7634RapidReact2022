// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.subsystems;

import java.lang.Math;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class DriveTrainSub extends SubsystemBase {
  /** Creates a new DriveTrainSub. */
  // I know am i werid.  I name stuff right to left.  Is that so bad?
  private WPI_VictorSPX rightDrive1;
  private WPI_VictorSPX rightDrive2;
  private WPI_VictorSPX leftDrive1;
  private WPI_VictorSPX leftDrive2;

  // The encoders might be handled by an arduino in the future.
  private Encoder rightEncoder;
  private Encoder leftEncoder;

  public AHRS navX;

  public DriveTrainSub() {
    rightDrive1 = new WPI_VictorSPX(Constants.RIGHT_DRIVE_1);
    rightDrive2 = new WPI_VictorSPX(Constants.RIGHT_DRIVE_2);

    rightDrive1.configFactoryDefault();
    rightDrive2.configFactoryDefault();
    rightDrive1.setInverted(true);
    rightDrive2.setInverted(true);
    rightDrive2.follow(rightDrive1);

    leftDrive1 = new WPI_VictorSPX(Constants.LEFT_DRIVE_1);
    leftDrive2 = new WPI_VictorSPX(Constants.LEFT_DRIVE_2);

    leftDrive1.configFactoryDefault();
    leftDrive2.configFactoryDefault();
    //leftDrive1.setInverted(true);
    //leftDrive2.setInverted(true);
    leftDrive2.follow(leftDrive1);

    rightEncoder = new Encoder(new DigitalInput(Constants.R_DRIVE_ENCODER_A), 
    new DigitalInput(Constants.R_DRIVE_ENCODER_B), false);

    leftEncoder = new Encoder(new DigitalInput(Constants.L_DRIVE_ENCODER_A), 
    new DigitalInput(Constants.L_DRIVE_ENCODER_B), true);

    rightEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DIS_PER_PULSE);
    leftEncoder.setDistancePerPulse(Constants.DRIVE_ENCODER_DIS_PER_PULSE);

    resetEncoders();

    navX = new AHRS(I2C.Port.kMXP, Constants.NAVX_UPDATE_RATE);
    resetNavx();
  }

  // Is a hot dog a sandwhich?
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

  public void resetEncoders() {
    rightEncoder.reset();
    leftEncoder.reset();
  }

  public double getRightEncoderDis() {
    return rightEncoder.getDistance();
  }

  public double getLeftEncoderDis() {
    return leftEncoder.getDistance();
  }

  public void resetNavx() {
    navX.reset();
    navX.zeroYaw();
  }

  public double getAvgDis() {
    return (getRightEncoderDis() + getLeftEncoderDis()) / 2.0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
