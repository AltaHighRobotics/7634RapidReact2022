// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.util.MathTools;
import java.lang.Math;

public class AutoDriveTo extends CommandBase {
  /** Creates a new AutoDriveTo. */
  private final DriveTrainSub m_driveTrainSub;
  private double m_driveTo;

  private double rightSpeed;
  private double leftSpeed;

  private double rightDis;
  private double leftDis;
  private double oldRightDis;
  private double oldLeftDis;

  private double yaw;
  private double turnPower;
  private double totalDis;
  private double disToEnd;

  private double direction;

  // Why do hot dogs come in differnt size packs than hot dog buns?

  public AutoDriveTo(DriveTrainSub driveTrainSub, double driveTo) {
    m_driveTrainSub = driveTrainSub;
    m_driveTo = driveTo;

    if (m_driveTo < 0) {
      direction = Constants.BACKWARD;
    } else {
      direction = Constants.FORWARD;
    }

    addRequirements(m_driveTrainSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrainSub.resetEncoders();
    m_driveTrainSub.resetNavx();

    yaw = 0.0;
    oldRightDis = 0.0;
    oldLeftDis = 0.0;
    totalDis = 0.0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    yaw = m_driveTrainSub.navX.getYaw();
    rightDis = m_driveTrainSub.getRightEncoderDis();
    leftDis = m_driveTrainSub.getLeftEncoderDis();

    turnPower = -yaw * Constants.AUTO_TURN_CORRECT * direction;
    totalDis += ((rightDis - oldRightDis) + (leftDis - oldLeftDis)) / 2.0;

    rightSpeed = -(Constants.AUTO_DRIVE_SPEED - turnPower) * direction;
    leftSpeed = -(Constants.AUTO_DRIVE_SPEED + turnPower) * direction;

    disToEnd = Math.abs(totalDis - m_driveTo);

    // Slow near end.
    if (disToEnd <= Constants.AUTO_DRIVE_SLOW_AT) {
      rightSpeed *= disToEnd / Constants.AUTO_DRIVE_SLOW_AT;
      leftSpeed *= disToEnd / Constants.AUTO_DRIVE_SLOW_AT;
    }

    m_driveTrainSub.setRightMotors(rightSpeed);
    m_driveTrainSub.setLeftMotors(leftSpeed);

    oldRightDis = rightDis;
    oldLeftDis = leftDis;

    SmartDashboard.putNumber("Right dis", rightDis);
    SmartDashboard.putNumber("Left dis", leftDis);
    SmartDashboard.putNumber("Dis", totalDis);
    SmartDashboard.putNumber("yaw", yaw);
    SmartDashboard.putNumber("TurnPower", turnPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrainSub.setMotors(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    disToEnd = Math.abs(totalDis - m_driveTo);

    if (disToEnd <= Constants.DRIVE_MIN) {
      return true;
    }

    return false;
  }
}
