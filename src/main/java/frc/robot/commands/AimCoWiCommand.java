// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AimSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AimCoWiCommand extends CommandBase {
  private final AimSub m_aimSub;

  /** Creates a new AimCommand. */
  public AimCoWiCommand(AimSub aimSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_aimSub = aimSub;
    addRequirements(m_aimSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Does counter clockwise turn
    if(m_aimSub.coAllow){
      m_aimSub.roAimCO(0.2);
    } else {
      m_aimSub.stopAim();
    }
    
    SmartDashboard.putString("dir", "COWI");
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
