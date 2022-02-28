// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSub extends SubsystemBase {
  private Solenoid liftPiston;
  private Victor winchMotor;
  /** Creates a new ClimbSub. */
  public ClimbSub() {
    winchMotor = new Victor(Constants.WINCH_MOTOR);
    liftPiston = new Solenoid(PneumaticsModuleType.REVPH, Constants.LIFT_PISTON);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void liftArmUp() {
    liftPiston.set(true);
  }

  public void pullArmDown() {
    liftPiston.set(false);
    winchMotor.set(Constants.WINCH_SPEED);
  }

  public void stopArm() {
    winchMotor.set(0);
  }
}