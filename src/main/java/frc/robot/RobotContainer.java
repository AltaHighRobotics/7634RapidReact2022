// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.*;
import frc.robot.autonomous.*;
import frc.robot.subsystems.*;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  private final LimelightSub m_limeSub = new LimelightSub();

  // Commands.
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveTrainSub, driveController);
  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_intakeSub);
  private final FeederCommand m_feederCommand = new FeederCommand(m_feederSub);
  private final LiftRobotCommand m_liftRobotCommand = new LiftRobotCommand(m_climbSub);
  private final RaiseLiftArmCommand m_raiseLiftArmCommand = new RaiseLiftArmCommand(m_climbSub);
  private final RevWinchCommand m_revWinchCommand = new RevWinchCommand(m_climbSub);

  private final LimelightCommand m_limeCommand = new LimelightCommand(m_limeSub, m_aimSub);

  private final AimClWiCommand m_aimClWiCommand = new AimClWiCommand(m_aimSub);
  private final AimCoWiCommand m_aimCoWiCommand = new AimCoWiCommand(m_aimSub);
  private final AimCenCommand m_aimCenCommand = new AimCenCommand(m_aimSub);

  private ShooterCommand m_shootCommand = new ShooterCommand(m_shooterSub);

  // Autonomous.
  private final SequentialCommandGroup m_testAuto = new SequentialCommandGroup(
    new AutoIntake(m_intakeSub, true),
    new AutoTurnTo(m_driveTrainSub, 90),
    new AutoIntake(m_intakeSub, false)
  );

  private final AutoTurnTo m_testTurn = new AutoTurnTo(m_driveTrainSub, 270);
  private final AutoDriveTo m_testDrive = new AutoDriveTo(m_driveTrainSub, 100);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //society

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure autonomous stuff.
    m_chooser.setDefaultOption("Test auto", m_testAuto);
    m_chooser.addOption("Test turn", m_testTurn);
    m_chooser.addOption("Test drive", m_testDrive);

    SmartDashboard.putData(m_chooser);

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
    final JoystickButton revWinchButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_11);
    final JoystickButton raiseArmButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_9);
    final JoystickButton pullArmButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_12);
    final JoystickButton shootButton = new JoystickButton(driveController, Constants.XBOX_B_BUTTON);

    final JoystickButton limeButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_5);
    
    final JoystickButton aimClButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_8);
    final JoystickButton aimCoButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_7);
    final JoystickButton aimCenButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_6);
    /*
    */

    // Is goofy a dog?
    aimClButton.whileHeld(m_aimClWiCommand);
    aimCoButton.whileHeld(m_aimCoWiCommand);
    aimCenButton.whileHeld(m_aimCenCommand);

    intakeButton.toggleWhenPressed(m_intakeCommand);
    feederButton.whileHeld(m_feederCommand);
    shootButton.toggleWhenPressed(m_shootCommand);

    limeButton.toggleWhenPressed(m_limeCommand);

    raiseArmButton.toggleWhenPressed(m_raiseLiftArmCommand);
    pullArmButton.whileHeld(m_liftRobotCommand);
    revWinchButton.whileHeld(m_revWinchCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}