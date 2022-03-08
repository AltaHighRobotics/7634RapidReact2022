// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.Constants;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final XboxController driveController = new XboxController(Constants.DRIVER_CONTROLLER);

  // Subsystems.
  private final DriveTrainSub m_driveTrainSub = new DriveTrainSub();
  private final IntakeSub m_intakeSub = new IntakeSub();
  private final FeederSub m_feederSub = new FeederSub();
  private final ClimbSub m_climbSub = new ClimbSub();
  private final AimSub m_aimSub = new AimSub();
  private final ShooterSub m_shooterSub = new ShooterSub();

  // Commands.
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveTrainSub, driveController);
  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_intakeSub);
  private final FeederCommand m_feederCommand = new FeederCommand(m_feederSub);
  private final LiftRobotCommand m_liftRobotCommand = new LiftRobotCommand(m_climbSub);
  private final RaiseLiftArmCommand m_raiseLiftArmCommand = new RaiseLiftArmCommand(m_climbSub);

  private final AimClWiCommand m_aimClWiCommand = new AimClWiCommand(m_aimSub);
  private final AimClWiCommand m_aimCoWiCommand = new AimClWiCommand(m_aimSub);

  private ShooterCommand m_shootCommand = new ShooterCommand(m_shooterSub);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    CommandScheduler.getInstance().setDefaultCommand(m_driveTrainSub, m_driveCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Set buttons.
    final JoystickButton intakeButton = new JoystickButton(driveController, Constants.XBOX_X_BUTTON);
    final JoystickButton feederButton = new JoystickButton(driveController, Constants.XBOX_A_BUTTON);
    

    /*
    final JoystickButton shootButton = new JoystickButton(driveController, Constants.XBOX_B_BUTTON);

    final JoystickButton aimClButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_10);
    final JoystickButton aimCoButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_9);

    */
    intakeButton.toggleWhenPressed(m_intakeCommand);
    feederButton.whileHeld(m_feederCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}