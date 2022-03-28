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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.Constants;

public class IntakeExtensionSub extends SubsystemBase {
  //Pistons.
  private Solenoid liftPiston1;
  private Solenoid liftPiston2;
  //Velcro motor.
  private WPI_VictorSPX velcroMotor;

  /** Creates a new IntakeExtensionSub. */
  public IntakeExtensionSub() {
    liftPiston1 = new Solenoid(PneumaticsModuleType.REVPH, Constants.LIFT_PISTON_1);
    liftPiston2 = new Solenoid(PneumaticsModuleType.REVPH, Constants.LIFT_PISTON_2);
    velcroMotor = new WPI_VictorSPX(Constants.VELCRO_MOTOR);

    velcroMotor.configFactoryDefault();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  //Creates method for lifting both arms.
  public void extendIntake() {
    liftPiston1.set(true);
    liftPiston2.set(true);
  }
  //Creates method for stopping both arms.
  public void retractIntake() {
    liftPiston1.set(false);
    liftPiston2.set(false);
  }
}