// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Jaden
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

//Make variable of ClimbSub.
public class RaiseLiftArmCommand extends CommandBase {
  private ClimbSub m_climbSub;
  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);


  /** Creates a new ClimbCommand. */
  public RaiseLiftArmCommand(ClimbSub climbSub) {
    m_climbSub = climbSub;
    addRequirements(m_climbSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  //Does doArm() method every time when it is called.
  @Override
  public void execute() {
    //m_climbSub.liftArmUp();
    m_climbSub.doArm();
    phCompressor.enableDigital();

    boolean enabled = phCompressor.enabled();
    boolean pressureSwitch = phCompressor.getPressureSwitchValue();
    double current = phCompressor.getCurrent();

  }

  // Called once the command ends or is interrupted.
  //When command is stopped, start method: stopArm().
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
