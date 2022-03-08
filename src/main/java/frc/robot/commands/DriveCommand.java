// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/*
  @Hacker
  @New Hawks
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSub;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class DriveCommand extends CommandBase {
  /** Creates a new DriveCommand. */
  private final DriveTrainSub m_driveTrainSub;
  private final XboxController m_driveController;

  private double rightStickX;
  private double rightStickY;
  private double leftStickX;
  private double leftStickY;
  private double stickZ;

  private double multiplier;
  private double lowTurnMultiplier;

  private double rightSpeed;
  private double leftSpeed;

  public DriveCommand(DriveTrainSub driveTrainSub, XboxController driveController) {
    m_driveTrainSub = driveTrainSub;
    m_driveController = driveController;

    addRequirements(m_driveTrainSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rightStickY = m_driveController.getRawAxis(Constants.RIGHT_STICK_Y);
    rightStickX = m_driveController.getRawAxis(Constants.RIGHT_STICK_X);
    leftStickX = m_driveController.getRawAxis(Constants.LEFT_STICK_X);
    leftStickY = m_driveController.getRawAxis(Constants.LEFT_STICK_Y);
    stickZ = m_driveController.getRawAxis(Constants.STICK_Z);

    multiplier = speedMultiplier(rightStickY);

    // Better turning.
    if (multiplier <= Constants.LOW_TURN_THRESHHOLD) {
      lowTurnMultiplier = Constants.LOW_TURN_MULTIPLIER;
    } else {
      lowTurnMultiplier = 1.0;
    }

    rightSpeed = (leftStickY + ((leftStickX + stickZ) * Constants.TURN_RAD * lowTurnMultiplier)) * multiplier;
    leftSpeed = (leftStickY - ((leftStickX + stickZ) * Constants.TURN_RAD * lowTurnMultiplier)) * multiplier;

    m_driveTrainSub.setRightMotors(rightSpeed);
    m_driveTrainSub.setLeftMotors(leftSpeed);
  }

  private double speedMultiplier(double stick) {
    return (stick - 1.0) / -2.0 + 0.5;
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
