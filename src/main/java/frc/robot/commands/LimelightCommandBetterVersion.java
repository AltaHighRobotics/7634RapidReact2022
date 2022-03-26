// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.AimSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import java.lang.Math;

public class LimelightCommandBetterVersion extends CommandBase {
  /** Creates a new LimelightCommandBetterVersion. */
  private final LimelightSub m_limelightSub;
  private final AimSub m_aimSub;

  public LimelightCommandBetterVersion(LimelightSub limelightSub, AimSub aimSub) {
    m_limelightSub = limelightSub;
    m_aimSub = aimSub;

    addRequirements(m_limelightSub, m_aimSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Aimbot", true);
    m_limelightSub.runLimeNum();

    // Vic, why did you make the varibles in limelightSub static!
    if (LimelightSub.targetSeen) {
      targetSeen();
    } else {
      targetNotSeen();
    }
  }

  private void targetSeen() {
    // Stay on target.

    // To right.
    if (LimelightSub.toRight && m_aimSub.clAllow) {

      if (LimelightSub.absX < Constants.AIM_THRESH){
        m_aimSub.roAimCL(LimelightSub.absX * Constants.AIM_SPEED / Constants.AIM_THRESH);
      } else {
        m_aimSub.roAimCL(Constants.AIM_SPEED);
      }

    // To left.
    } else if (!LimelightSub.toRight && m_aimSub.coAllow) {

      if (LimelightSub.absX < Constants.AIM_THRESH){ 
        m_aimSub.roAimCO(LimelightSub.absX * Constants.AIM_SPEED / Constants.AIM_THRESH);
      } else {
        m_aimSub.roAimCO(Constants.AIM_SPEED);
      }

    } else {
      m_aimSub.stopAim();
    }
  }

  private void targetNotSeen() {
    // Search for target.

    if (LimelightSub.goingCL) {
      m_aimSub.rotateAimCL(Constants.AIM_ROTATE_SNAIL_SPEED);
    } else {
      m_aimSub.rotateAimCO(Constants.AIM_ROTATE_SNAIL_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Aimbot", false);
    LimelightSub.targetSeen = false;
    m_aimSub.stopAim();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
