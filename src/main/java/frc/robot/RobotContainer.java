// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.print.DocFlavor.SERVICE_FORMATTED;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
  private final XboxController shotController = new XboxController(Constants.SHOTGUN_CONTROLLER);

  // Subsystems.
  private final DriveTrainSub m_driveTrainSub = new DriveTrainSub();
  private final IntakeSub m_intakeSub = new IntakeSub();
  private final FeederSub m_feederSub = new FeederSub();
  private final ClimbSub m_climbSub = new ClimbSub();
  private final AimSub m_aimSub = new AimSub();
  private final ShooterSub m_shooterSub = new ShooterSub();
  private final LimelightSub m_limeSub = new LimelightSub();
  private final IntakeExtensionSub m_intakeExtensionSub = new IntakeExtensionSub();

  // Commands.
  private final DriveCommand m_driveCommand = new DriveCommand(m_driveTrainSub, driveController);
  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_intakeSub);
  private final FeederCommand m_feederCommand = new FeederCommand(m_feederSub);
  private final LiftRobotCommand m_liftRobotCommand = new LiftRobotCommand(m_climbSub);
  private final RaiseLiftArmCommand m_raiseLiftArmCommand = new RaiseLiftArmCommand(m_climbSub);
  private final RevWinchCommand m_revWinchCommand = new RevWinchCommand(m_climbSub);
  private WinchCommand m_winchCommand = new WinchCommand(m_climbSub, shotController);

  private final LimelightCommand m_limeCommand = new LimelightCommand(m_limeSub, m_aimSub);
  private final LimelightCommandBetterVersion m_limeCommand2 = new LimelightCommandBetterVersion(m_limeSub, m_aimSub);

  private final AimClWiCommand m_aimClWiCommand = new AimClWiCommand(m_aimSub);
  private final AimCoWiCommand m_aimCoWiCommand = new AimCoWiCommand(m_aimSub);
  private final AimCenCommand m_aimCenCommand = new AimCenCommand(m_aimSub);
  private final AimerCommand m_aimerCommand = new AimerCommand(m_aimSub, shotController);

  private ShooterCommand m_shootCommand = new ShooterCommand(m_shooterSub);

  private final ExtendIntakeCommand m_extendIntakeCommand = new ExtendIntakeCommand(m_intakeExtensionSub);
  private final VelcroMotorCommand m_velcroMotorCommand = new VelcroMotorCommand(m_intakeExtensionSub);
  private final RevVelcroMotorCommand m_revVelcroMotorCommand = new RevVelcroMotorCommand(m_intakeExtensionSub);

  // Autonomous.
  private final SequentialCommandGroup m_testAuto = new SequentialCommandGroup(
    new AutoTurnTo(m_driveTrainSub, -60.0)
  );

  // THIS AUTONOMOUS CODE WILL BURN YOUR EYES OUT OF THEIR SOCKETS!!!!!!
  private final SequentialCommandGroup m_doubleSide1 = new SequentialCommandGroup(
    new AutoTurnTo(m_driveTrainSub, 25.09),
    new AutoIntake(m_intakeSub, true),
    new AutoDriveTo(m_driveTrainSub, 66.665), // Gets ball.
    new AutoTurnTo(m_driveTrainSub, 180.0),
    new AutoShooter(m_shooterSub, true),
    new AutoDriveTo(m_driveTrainSub, 40.451),
    //new AutoWaitForAim(), // Shoot.
    new AutoFeeder(m_feederSub, true),
    new AutoWaitFor(1.0),
    new AutoFeeder(m_feederSub, false),
    new AutoShooter(m_shooterSub, false),
    new AutoTurnTo(m_driveTrainSub, 197.4), // 180 - 17.4deg counter.
    new AutoDriveTo(m_driveTrainSub, 162.54),
    new AutoTurnTo(m_driveTrainSub, 41.19),
    new AutoDriveTo(m_driveTrainSub, 36.441), // Gets ball.
    new AutoIntake(m_intakeSub, false)
  );

  private final SequentialCommandGroup m_doubleMid1 = new SequentialCommandGroup(
    //new AutoTurnTo(m_driveTrainSub, 3.09),
    new AutoIntake(m_intakeSub, true),
    new AutoDriveTo(m_driveTrainSub, 52.523), // Gets ball.
    new AutoTurnTo(m_driveTrainSub, 180.0),
    new AutoShooter(m_shooterSub, true),
    new AutoDriveTo(m_driveTrainSub, 29.537),
    //new AutoWaitForAim(), // Shoot.
    new AutoFeeder(m_feederSub, true),
    new AutoWaitFor(1.0),
    new AutoFeeder(m_feederSub, false),
    new AutoShooter(m_shooterSub, false),
    new AutoTurnTo(m_driveTrainSub, 95.3),
    new AutoDriveTo(m_driveTrainSub, 113.925), // Gets ball.
    new AutoIntake(m_intakeSub, false)
  );

  private final SequentialCommandGroup m_sinMid1 = new SequentialCommandGroup(
    //new AutoTurnTo(m_driveTrainSub, 356.9), // 180 - 176.9 counter.
    new AutoIntake(m_intakeSub, true),
    new AutoDriveTo(m_driveTrainSub, 61.541), // Gets ball.
    new AutoTurnTo(m_driveTrainSub, 180.0),
    new AutoShooter(m_shooterSub, true),
    new AutoDriveTo(m_driveTrainSub, 39.558),
    //new AutoWaitForAim(), // Shoot.
    new AutoFeeder(m_feederSub, true),
    new AutoWaitFor(1.0),
    new AutoFeeder(m_feederSub, false),
    new AutoShooter(m_shooterSub, false),
    new AutoTurnTo(m_driveTrainSub, 264.7), // 180 - 84.7deg counter.
    new AutoDriveTo(m_driveTrainSub, 113.936), // Gets ball.
    new AutoIntake(m_intakeSub, false)
  );

  // The pain is over for now.

  private final RapidReactAutoCommand m_sinMidBetterVersion = new RapidReactAutoCommand(m_driveTrainSub, m_intakeSub, m_shooterSub, m_feederSub);

  private final AutoTurnTo m_testTurn = new AutoTurnTo(m_driveTrainSub, 270);
  private final AutoDriveTo m_testDrive = new AutoDriveTo(m_driveTrainSub, 100);

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //society
  //hmmmmmm.....

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure autonomous stuff.
    m_chooser.setDefaultOption("sin mid 1 better version", m_sinMidBetterVersion);
    m_chooser.addOption("Test auto", m_testAuto);
    m_chooser.addOption("Test turn", m_testTurn);
    m_chooser.addOption("Test drive", m_testDrive);
    m_chooser.addOption("double side 1", m_doubleSide1);
    m_chooser.addOption("double mid 1", m_doubleMid1);
    m_chooser.addOption("sin mid 1", m_sinMid1);

    SmartDashboard.putData(m_chooser);
    //m_limeSub.ledOff();

    // Configure the button bindings
    configureButtonBindings();
    CommandScheduler.getInstance().setDefaultCommand(m_driveTrainSub, m_driveCommand);
    CommandScheduler.getInstance().setDefaultCommand(m_aimSub, m_aimerCommand);
    CommandScheduler.getInstance().schedule(m_aimerCommand);
    CommandScheduler.getInstance().schedule(m_winchCommand);
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
    final JoystickButton revWinchButton = new JoystickButton(shotController, Constants.FLIGHT_BUTTON_5);
    final JoystickButton raiseArmButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_9);
    final JoystickButton pullArmButton = new JoystickButton(shotController, Constants.FLIGHT_BUTTON_6);
    final JoystickButton shootButton = new JoystickButton(driveController, Constants.XBOX_B_BUTTON);
    final JoystickButton extendIntakeButton = new JoystickButton(driveController, Constants.XBOX_X_BUTTON);
    final JoystickButton velcroMotorButton = new JoystickButton(driveController, Constants.XBOX_X_BUTTON);

    final JoystickButton limeButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_5);
    
    final JoystickButton aimClButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_8);
    final JoystickButton aimCoButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_7);
    final JoystickButton aimCenButton = new JoystickButton(driveController, Constants.FLIGHT_BUTTON_6);

    //For Second Controller
    final JoystickButton raiseArmButton2 = new JoystickButton(shotController, Constants.XBOX_A_BUTTON);

    /*
    */

    // Is goofy a dog?
    //We have already been through this, yes.
    aimClButton.whileHeld(m_aimClWiCommand);
    aimCoButton.whileHeld(m_aimCoWiCommand);
    aimCenButton.whileHeld(m_aimCenCommand);

    intakeButton.toggleWhenPressed(m_intakeCommand);
    feederButton.whileHeld(m_feederCommand);
    shootButton.whileHeld(m_shootCommand);
    extendIntakeButton.whileHeld(m_extendIntakeCommand);
    velcroMotorButton.toggleWhenPressed(m_velcroMotorCommand);

    limeButton.toggleWhenPressed(m_limeCommand2);

    raiseArmButton.whileHeld(m_raiseLiftArmCommand);
    pullArmButton.whileHeld(m_liftRobotCommand);
    revWinchButton.whileHeld(m_revWinchCommand);

    //Second Controller
    raiseArmButton2.whileHeld(m_raiseLiftArmCommand);
    

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