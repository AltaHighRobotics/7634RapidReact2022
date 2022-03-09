// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class ClimbSub extends SubsystemBase {
  private Solenoid liftPiston;
  private VictorSPX winchMotor;
  private double xPOV = Robot.xboxPOV;

  /** Creates a new ClimbSub. */
  public ClimbSub() {
    winchMotor = new VictorSPX(Constants.WINCH_MOTOR);
    liftPiston = new Solenoid(PneumaticsModuleType.REVPH, Constants.LIFT_PISTON);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    xPOV = Robot.xboxPOV;
  }

  public void liftArmUp() {
    liftPiston.set(true);
  }

  public void pullArmDown() {
    liftPiston.set(false);
    winchMotor.set(ControlMode.PercentOutput, Constants.WINCH_SPEED);
  }

  public void stopArm() {
    liftPiston.set(false);
    winchMotor.set(ControlMode.PercentOutput, 0.0);
  }
  
  public void revWinch() {
    //liftPiston.set(false);
    winchMotor.set(ControlMode.PercentOutput, -Constants.WINCH_SPEED);
  }

  public void doArm(){
    int pov = (int)xPOV;
    switch(pov){
      case 0: //Dpad up
        SmartDashboard.putString("armcom", "lif arm up");
        liftArmUp();
      break;

      case 180: //Dpad down
        SmartDashboard.putString("armcom", "pull arm down");
        pullArmDown();
      break;

      case 90:
        //rev winch
        revWinch();
      break;

      case -1: //Dpad neutral
        SmartDashboard.putString("armcom", "stops arm");
        stopArm();
      break;
    }
  }
}