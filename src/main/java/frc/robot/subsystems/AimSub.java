// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.LimelightSub;

public class AimSub extends SubsystemBase {
  private TalonSRX aimMotor;

  private double encNum;

  public boolean coAllow;
  public boolean clAllow;

  public boolean centerCL;
  public boolean centered;
  public boolean goSlow;

  /** Creates a new AimSub. */
  public AimSub() {
    aimMotor = new TalonSRX(Constants.AIM_MOTOR);
    aimMotor.configFactoryDefault();
    
  }

  @Override
  public void periodic() {
    encNum = aimMotor.getSelectedSensorPosition();

    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Enc Dist", encNum);

    //Checks if within limits
    if(encNum > Constants.MAX_AIM_CL){
      clAllow = true;
      
    } else {
      clAllow = false;
      if(LimelightSub.goingCL){
        LimelightSub.goingCL = false;
      }
    }

    //Checks if within limits
    if(encNum < Constants.MAX_AIM_CO){
      coAllow = true;
    } else {
      coAllow = false;
      if(!LimelightSub.goingCL){
        LimelightSub.goingCL = true;
      }
    }

    //Finds if motor should turn CO/CL for center
    if(encNum < -10){
      //clockwise
      centerCL = false;
      centered = false;
      
    } else if (encNum > 10){
      //counterclockwise
      centerCL = true;
      centered = false;

    } else {
      centered = true;
    }

    if(encNum < -500 || encNum > 500){
      //go slower
      goSlow = false;
    } else {
      goSlow = true;
    }
    SmartDashboard.putBoolean("goslow aim", goSlow);
  }

  public void rotateAimCL(int slow) { //0 = norm; 1 = slow; 2 = extra slow
    //Rotates the aimer clockwise
      if (slow == 1){
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SLOW_SPEED); 
      } else if (slow == 0) {
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SPEED);
      } else if (slow == 2) {
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SNAIL_SPEED);
      } else {
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_ELLA_SPEED);
      }
  }

  public void rotateAimCO(int slow){
      //Rotates the aimer counter clockwise
      if(slow == 1){
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SLOW_SPEED);
      } else if (slow == 0) {
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SPEED);
      } else  if (slow == 2) {
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SNAIL_SPEED);
      } else {
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_ELLA_SPEED);
      }
  }

  public void stopAim(){
    //Stops the aimer motor
    aimMotor.set(ControlMode.PercentOutput, Constants.AIM_STOP);
  }
}