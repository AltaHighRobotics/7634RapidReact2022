// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Jaden
  @New Hawks 
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeExtensionSub;

public class RevVelcroMotorCommand extends CommandBase {
  private final IntakeExtensionSub m_intakeExtensionSub;
  /** Creates a new RevVelcroMotorCommand. */
  public RevVelcroMotorCommand(IntakeExtensionSub intakeExtensionSub) {
    m_intakeExtensionSub = intakeExtensionSub;
    addRequirements(m_intakeExtensionSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeExtensionSub.reverseIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeExtensionSub.retractIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
