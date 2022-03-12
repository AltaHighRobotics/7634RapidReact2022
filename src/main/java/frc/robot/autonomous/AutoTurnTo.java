// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

// I omost feel bad for u but at the same time I DONT CARE ABOUT YOUR FEELINGS!

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.util.MathTools;
import frc.robot.Constants;

public class AutoTurnTo extends CommandBase {
  /** Creates a new AutoTurnTo. */
  private final DriveTrainSub m_driveTrain;
  private double m_turnTo;
  private double m_turnDirection; // 1 or -1
 
  // The different between turnTo and current angle.
  private double turnError;

  public AutoTurnTo(DriveTrainSub driveTrain, double turnTo, double turnDirection) {
    m_driveTrain = driveTrain;
    m_turnTo = turnTo;
    m_turnDirection = turnDirection;

    addRequirements(m_driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.resetNavx();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.setRightMotors(-Constants.AUTO_TURN_SPEED * m_turnDirection);
    m_driveTrain.setLeftMotors(Constants.AUTO_TURN_SPEED * m_turnDirection);
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
