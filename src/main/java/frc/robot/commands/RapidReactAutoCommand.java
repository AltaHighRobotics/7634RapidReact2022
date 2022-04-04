// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.ShooterSub;
import frc.robot.subsystems.FeederSub;
import frc.robot.util.MathTools;
import java.lang.Math;

public class RapidReactAutoCommand extends CommandBase {
  private int stage;
  private int c;
  private double leftDist;
  private double rightDist;
  private double avgDist;
  private double yaw;
  private double rightSpeed;
  private double leftSpeed;
  private double turnPower;
  private double turnError;
  private double turnDirection;
  private DriveTrainSub m_driveSub;
  private IntakeSub m_intakeSub;
  private ShooterSub m_shootSub;
  private FeederSub m_feederSub;
  /** Creates a new RapidReactAutoCommand. */
  public RapidReactAutoCommand(DriveTrainSub drivetrain, IntakeSub intakeSub, ShooterSub shootSub, FeederSub feederSub) {
    m_driveSub = drivetrain;
    m_intakeSub = intakeSub;
    m_shootSub = shootSub;
    m_feederSub = feederSub;

    addRequirements(m_shootSub, m_intakeSub, m_driveSub, m_feederSub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveSub.resetEncoders();
    m_driveSub.resetNavx();

    stage = 1;
    c = 0;
    leftDist = 0;
    rightDist = 0;
    avgDist = 0;
    rightSpeed = 0;
    leftSpeed = 0;
    yaw = 0;
    turnPower = 0;
    turnError = 0;
    turnDirection = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSub.startMotor();
    m_shootSub.startShooterMotor();

    SmartDashboard.putNumber("AUTO", stage);

    switch (stage)
    {
      case 1:
      //TODO make drivetrain go pick up first ball
        rightDist = m_driveSub.getRightEncoderDis();
        leftDist = m_driveSub.getLeftEncoderDis();
        avgDist = Math.abs(m_driveSub.getAvgDis());
        yaw = m_driveSub.navX.getYaw();
        turnPower = -yaw * Constants.AUTO_TURN_CORRECT;

        m_driveSub.setRightMotors(-Constants.AUTO_DRIVE_SPEED);
        m_driveSub.setLeftMotors(-Constants.AUTO_DRIVE_SPEED);

        if(avgDist >= Constants.AUTO_DISTANCE1) {
          stage = 2;
          m_driveSub.setMotors(0.0);
          m_driveSub.resetEncoders();
          m_driveSub.resetNavx();
        }

        break;

      case 2:
      //TODO make drive backwards to shoot pos
        rightDist = m_driveSub.getRightEncoderDis();
        leftDist = m_driveSub.getLeftEncoderDis();
        avgDist = Math.abs(m_driveSub.getAvgDis());
        yaw = m_driveSub.navX.getYaw();
        turnPower = yaw * Constants.AUTO_TURN_CORRECT;

        rightSpeed = Constants.AUTO_DRIVE_SPEED - turnPower;
        leftSpeed = Constants.AUTO_DRIVE_SPEED + turnPower;
        m_driveSub.setRightMotors(Constants.AUTO_DRIVE_SPEED);
        m_driveSub.setLeftMotors(Constants.AUTO_DRIVE_SPEED);

        if(avgDist >= Constants.AUTO_DISTANCE_SHOOT1)
        {
          stage = 3;
          m_driveSub.setMotors(0.0);
          m_driveSub.resetEncoders();
          m_driveSub.resetNavx();
        }
        break;

      case 3:
        //TODO make drive train turn around
        turnDirection = Constants.CLOCK_WISE;
        yaw = m_driveSub.navX.getYaw();
        turnError = MathTools.angleDis(yaw, Constants.AUTO_TURN_DISTANCE);
        SmartDashboard.putNumber("Turn error", turnError);
            
        if (turnError <= Constants.AUTO_TURN_SLOWDOWN_DIS) {
          m_driveSub.setRightMotors(-Constants.AUTO_TURN_SPEED * turnDirection * (turnError / Constants.AUTO_TURN_SLOWDOWN_DIS));
          m_driveSub.setLeftMotors(Constants.AUTO_TURN_SPEED * turnDirection * (turnError / Constants.AUTO_TURN_SLOWDOWN_DIS));
        } else {
          m_driveSub.setRightMotors(-Constants.AUTO_TURN_SPEED * turnDirection);
          m_driveSub.setLeftMotors(Constants.AUTO_TURN_SPEED * turnDirection);
        }

        SmartDashboard.putNumber("yaw", yaw);

        if(turnError <= Constants.TURN_MIN)
        {
          stage = 4;
          m_driveSub.resetEncoders();
          m_driveSub.resetNavx();
          m_driveSub.setMotors(0.0);
        }
        break;
      
        case 4:
          //TODO shoot ball lol
          c++;
          m_feederSub.startMotor();
          if (c >= Constants.AUTO_SHOOT_TIME)
          {
            stage = 5;
            m_driveSub.resetEncoders();
            m_driveSub.resetNavx();
            m_feederSub.stopMotor();
          }
          break;

        case 5:
          SmartDashboard.putString("AUTO", "IDLE");
          break;

        default:
          SmartDashboard.putString("AUTO", "ERROR BAD LOGIC");
          break;
          
        
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shootSub.stopShooterMotor();
    m_intakeSub.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
