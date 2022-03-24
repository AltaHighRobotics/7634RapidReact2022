// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSub;
import frc.robot.Constants;

public class AutoFeeder extends CommandBase {
  /** Creates a new AutoFeeder. */
  private final FeederSub m_feedersub;
  private boolean m_turnOn;

  public AutoFeeder(FeederSub feederSub, boolean turnOn) {
    m_feedersub = feederSub;
    m_turnOn = turnOn;

    addRequirements(m_feedersub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_turnOn) {
      m_feedersub.startMotor();
    } else {
      m_feedersub.stopMotor();
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
