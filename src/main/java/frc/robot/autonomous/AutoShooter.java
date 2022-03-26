// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSub;
import frc.robot.Constants;

public class AutoShooter extends CommandBase {
  /** Creates a new AutoShooter. */
  private final ShooterSub m_shooterSub;
  private boolean m_turnOn;

  public AutoShooter(ShooterSub shooterSub, boolean turnOn) {
    m_shooterSub = shooterSub;
    m_turnOn = turnOn;

    addRequirements(m_shooterSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_turnOn) {
      m_shooterSub.startShooterMotor();
    } else {
      m_shooterSub.stopShooterMotor();
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
