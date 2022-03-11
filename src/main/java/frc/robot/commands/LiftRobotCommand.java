// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Jaden
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSub;
//Creates variable of ClimbSub.
public class LiftRobotCommand extends CommandBase {
  private ClimbSub m_climbSub;
  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);

  /** Creates a new LiftRobot. */
  public LiftRobotCommand(ClimbSub climbSub) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climbSub = climbSub;
    addRequirements(m_climbSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  //While called, run doArm().
  @Override
  public void execute() {
    m_climbSub.pullArmDown();
    phCompressor.enableDigital();

    boolean enabled = phCompressor.enabled();
    boolean pressureSwitch = phCompressor.getPressureSwitchValue();
    double current = phCompressor.getCurrent();

    m_climbSub.doArm();
  }

  // Called once the command ends or is interrupted.
  //When the command is stopped, start method: stopArm().
  @Override
  public void end(boolean interrupted) {
    m_climbSub.stopArm();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
