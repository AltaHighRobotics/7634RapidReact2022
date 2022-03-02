// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.DriveTrainSub;

public class LimelightCommand extends CommandBase {
  private final DriveTrainSub m_driveTrainSub;
  private final LimelightSub m_limelightSub;

  /** Creates a new LimelightCommand. */
  public LimelightCommand(LimelightSub subsystem, DriveTrainSub driveTrainSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelightSub = subsystem;
    m_driveTrainSub = driveTrainSub;
    //subsystem dependencies
    addRequirements(m_limelightSub, m_driveTrainSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_limelightSub.runLimeNum();
    if(m_limelightSub.toRight){
      //m_driveTrainSub.setRightMotors(-0.2, 1);
      //m_driveTrainSub.setLeftMotors(0.2, 1);
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
