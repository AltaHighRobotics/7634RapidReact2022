// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSub;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

public class WinchCommand extends CommandBase {
  /** Creates a new WinchCommand. */
  private final ClimbSub m_climbSub;
  private final XboxController m_shotController;

  private double leftStickY;

  public WinchCommand(ClimbSub climbsub, XboxController shotController) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSub = climbsub;
    m_shotController = shotController;

    addRequirements(m_climbSub);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftStickY = m_shotController.getRawAxis(Constants.RIGHT_STICK_Y);

    if(leftStickY > 0){
      m_climbSub.liftArmUp();
    } else if (leftStickY < 0){
      m_climbSub.pullArmDown();
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
