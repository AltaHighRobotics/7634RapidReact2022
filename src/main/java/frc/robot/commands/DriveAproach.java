// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.LimelightSub;
import frc.robot.Constants;

public class DriveAproach extends CommandBase {
  private final LimelightSub m_limelightSub;
  private final DriveTrainSub m_driveTrainSub;

  /** Creates a new DriveAproach. */
  public DriveAproach(DriveTrainSub driveTrainSub, LimelightSub limelightSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelightSub = limelightSub;
    m_driveTrainSub = driveTrainSub;
    addRequirements(m_driveTrainSub, m_limelightSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_limelightSub.distInch > Constants.TARGET_DIST){
      //go forward
      m_driveTrainSub.setMotors(0.4);
    } else {
      m_driveTrainSub.setMotors(-0.4);
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
