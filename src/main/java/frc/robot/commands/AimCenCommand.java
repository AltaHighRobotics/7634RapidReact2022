// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AimSub;

public class AimCenCommand extends CommandBase {
  private final AimSub m_aimSub;

  /** Creates a new AimCenterCommand. */
  public AimCenCommand(AimSub aimSub) {
    // Use addRequirements() here to declare subsystem dependencies.

    m_aimSub = aimSub;

    addRequirements(m_aimSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!m_aimSub.centered){
      if(m_aimSub.centerCL){
        if(m_aimSub.goSlow){
          m_aimSub.rotateAimCL(3);
        } else {
        m_aimSub.rotateAimCL(0);
        }

      } else {
        if(m_aimSub.goSlow){
          m_aimSub.rotateAimCO(3);
        } else {
        m_aimSub.rotateAimCO(0);
        }
      }
    } else {
      m_aimSub.stopAim();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_aimSub.stopAim();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
