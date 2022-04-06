// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Jaden
  @New Hawks 
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Compressor;

import frc.robot.Robot;

//Creates Solenoid, Winch Motor, and Xbox POV for the DPad variables.
public class ClimbSub extends SubsystemBase {
  private Solenoid liftPiston;

  private WPI_VictorSPX winchMotor;
  
  /** Creates a new ClimbSub. */
  public ClimbSub() {
    winchMotor = new WPI_VictorSPX(Constants.WINCH_MOTOR);
    liftPiston = new Solenoid(PneumaticsModuleType.REVPH, Constants.LIFT_PISTON);

    winchMotor.configFactoryDefault();
    winchMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //Gets DPad.
   

  }
  //Creates method of lifting arm up and releasing air.
  public void liftArmUp() {
    liftPiston.set(true);
    System.out.println("Intake on.");
  }
  //Creates method to pull arm down to stop air and start up winch.
  public void pullArmDown() {
    liftPiston.set(false);  
    winchMotor.set(ControlMode.PercentOutput, Constants.WINCH_SPEED);
  }
  //This creates a method to stop the arm altogethe and to stop air and stop winch.
  public void stopArm() {
    liftPiston.set(false);
    winchMotor.set(ControlMode.PercentOutput, 0.0);
  }
  //This creates a method to make the winch rewind.
  public void revWinch() {
    //liftPiston.set(false);
    winchMotor.set(ControlMode.PercentOutput, -Constants.WINCH_SPEED);
  }
  //It does commands based on DPad.
  
    
  }
