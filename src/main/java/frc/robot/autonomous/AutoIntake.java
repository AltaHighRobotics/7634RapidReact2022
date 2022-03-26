// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSub;
import frc.robot.Constants;

public class AutoIntake extends CommandBase {
  /** Creates a new AutoIntake. */
  private final IntakeSub m_intakeSub;
  private boolean m_turnOn;

  public AutoIntake(IntakeSub intakeSub, boolean turnOn) {
    m_intakeSub = intakeSub;
    m_turnOn = turnOn;

    addRequirements(m_intakeSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_turnOn) {
      m_intakeSub.startMotor();
    } else {
      m_intakeSub.stopMotor();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
