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
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.commands.LimelightCommandBetterVersion;
import frc.robot.subsystems.LimelightSub;

public class CenterOnShootCommand extends CommandBase {
  private final AimSub m_aimSub;
  private final LimelightSub m_limelightSub;
  private final DriveTrainSub m_driveTrainSub;

  private final LimelightCommandBetterVersion m_limeCommand;

  /** Creates a new CenterOnShootCommand. */
  public CenterOnShootCommand(DriveTrainSub driveTrainSub, LimelightSub limelightSub, AimSub aimSub, LimelightCommandBetterVersion limeCommand) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelightSub = limelightSub;
    m_aimSub = aimSub;
    m_driveTrainSub = driveTrainSub;
    m_limeCommand = limeCommand;

    addRequirements(m_driveTrainSub, m_limelightSub, m_aimSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*
    STEPS :: 
     - aim to lime tar
     - move body so that encoder is 0
        - when centerCL; go CO
     - do whilst
     - repeat
    */

    m_limeCommand.execute();
    if(m_aimSub.centerCL){
      //drivetrain rotate CO
      m_driveTrainSub.setLeftMotors(-0.2);
      m_driveTrainSub.setRightMotors(0.2);
    } else {
      //drivetrain rotate CL
      m_driveTrainSub.setLeftMotors(0.2);
      m_driveTrainSub.setRightMotors(-0.2);
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
