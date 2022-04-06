// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @Jaden
  @Hacker
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AimSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.XboxController;

public class AimerCommand extends CommandBase {
  /** Creates a new AimerCommand. */
  public final AimSub m_aimSub;
  private final XboxController m_shotController;

  private double leftTrigger;
  private double rightTrigger;

  // your mothers anus

  public AimerCommand(AimSub aimSub, XboxController shotController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_aimSub = aimSub;
    m_shotController = shotController;

    addRequirements(m_aimSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftTrigger = m_shotController.getLeftTriggerAxis();
    rightTrigger = m_shotController.getRightTriggerAxis();

    SmartDashboard.putNumber("L TRIG", leftTrigger);
    SmartDashboard.putNumber("R TRIG", rightTrigger);
    SmartDashboard.putBoolean("clAllow", m_aimSub.clAllow);
    SmartDashboard.putBoolean("coallow", m_aimSub.coAllow);

    if (rightTrigger != 0.0 && m_aimSub.clAllow) {
      m_aimSub.roAimCL(rightTrigger * Constants.SHOT_AIM_MULT);
    } else if (leftTrigger != 0.0 && m_aimSub.coAllow) {
      m_aimSub.roAimCO(leftTrigger * Constants.SHOT_AIM_MULT);
    } else {
      m_aimSub.stopAim();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
