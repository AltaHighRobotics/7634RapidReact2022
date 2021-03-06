// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.AimSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public class LimelightCommand extends CommandBase {
  private final LimelightSub m_limelightSub;
  private final AimSub m_aimSub;

  /** Creates a new LimelightCommand. */
  public LimelightCommand(LimelightSub subsystem, AimSub aimSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelightSub = subsystem;
    m_aimSub = aimSub;
    //subsystem dependencies
    addRequirements(m_limelightSub, m_aimSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Aimbot", true);
    m_limelightSub.runLimeNum();
    
    //That's a little sus.
    if(!LimelightSub.toRight && LimelightSub.targetSeen){//clockwise aim to
      if(LimelightSub.absX < Constants.AIM_THRESH && m_aimSub.clAllow){
        m_aimSub.roAimCL(LimelightSub.absX * Constants.AIM_SPEED / Constants.AIM_THRESH);
      } else if (m_aimSub.clAllow) {
        m_aimSub.roAimCL(Constants.AIM_SPEED);
      }

    } else if (LimelightSub.toRight && LimelightSub.targetSeen && m_aimSub.coAllow) {//count clockwise aim to
      if(LimelightSub.absX < Constants.AIM_THRESH){
        m_aimSub.roAimCO(LimelightSub.absX * Constants.AIM_SPEED / Constants.AIM_THRESH);
      } else if (m_aimSub.coAllow){
        m_aimSub.roAimCO(Constants.AIM_SPEED);
      }

    } else if(!LimelightSub.targetSeen){
      //Do left right turning to try to find
      if(LimelightSub.goingCL){
        m_aimSub.rotateAimCL(2);
      } else {
        m_aimSub.rotateAimCO(2);
      }
    } else {
      m_aimSub.stopAim();
    }

    if(!LimelightSub.targetSeen){
      //Do left right turning to try to find
      if(LimelightSub.goingCL){
        m_aimSub.rotateAimCL(2);
      } else {
        m_aimSub.rotateAimCO(2);
      }
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
    LimelightSub.targetSeen = false;
    return false;
  }
}
