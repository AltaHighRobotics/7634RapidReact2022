// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

// I omost feel bad for u but at the same time I DONT CARE ABOUT YOUR FEELINGS!

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.util.MathTools;
import frc.robot.Constants;

public class AutoTurnTo extends CommandBase {
  /** Creates a new AutoTurnTo. */
  private final DriveTrainSub m_driveTrain;
  private double m_turnTo;
  private double turnDirection; // 1 or -1
 
  // The different between turnTo and current angle.
  private double turnError;
  
  private double yaw;

  public AutoTurnTo(DriveTrainSub driveTrain, double turnTo) {
    m_driveTrain = driveTrain;
    m_turnTo = turnTo;

    addRequirements(m_driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.resetNavx();

    // Find fastest direction to turn.
    if (m_turnTo >= 180.0) {
      turnDirection = Constants.CLOCK_WISE;
    } else {
      turnDirection = Constants.COUNTER_CLOCK_WISE;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    yaw = m_driveTrain.navX.getYaw();
    turnError = MathTools.angleDis(yaw, m_turnTo);
    SmartDashboard.putNumber("Turn error", turnError);

    // Switch direction.
    /*
    if (Math.abs(m_turnTo - MathTools.makeNonNegAngle(yaw)) >= 180.0) {
      turnDirection = Constants.CLOCK_WISE;
    } else {
      turnDirection = Constants.COUNTER_CLOCK_WISE;
    }
    */

    if (turnError <= Constants.AUTO_TURN_SLOWDOWN_DIS) {
      m_driveTrain.setRightMotors(-Constants.AUTO_TURN_SPEED * turnDirection * (turnError / Constants.AUTO_TURN_SLOWDOWN_DIS));
      m_driveTrain.setLeftMotors(Constants.AUTO_TURN_SPEED * turnDirection * (turnError / Constants.AUTO_TURN_SLOWDOWN_DIS));
    } else {
      m_driveTrain.setRightMotors(-Constants.AUTO_TURN_SPEED * turnDirection);
      m_driveTrain.setLeftMotors(Constants.AUTO_TURN_SPEED * turnDirection);
    }

    SmartDashboard.putNumber("yaw", yaw);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setMotors(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    turnError = MathTools.angleDis(m_driveTrain.navX.getYaw(), m_turnTo);

    if (turnError <= Constants.TURN_MIN) {
      return true;
    }

    return false;
  }
}
