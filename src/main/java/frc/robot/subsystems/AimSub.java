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

public class AimSub extends SubsystemBase {
  private TalonSRX aimMotor;

  private Encoder aimEncoder;

  public boolean coAllow;
  public boolean clAllow;

  /** Creates a new AimSub. */
  public AimSub() {
    aimEncoder = new Encoder(0, 1);
    aimEncoder.reset();
    aimMotor = new TalonSRX(Constants.AIM_MOTOR);
    aimMotor.configFactoryDefault();
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Enc Dist", aimMotor.getSelectedSensorPosition());

    if(aimMotor.getSelectedSensorPosition() > Constants.MAX_AIM_CL){
      clAllow = true;
    } else {
      clAllow = false;
    }

    if(aimMotor.getSelectedSensorPosition() < Constants.MAX_AIM_CO){
      coAllow = true;
    } else {
      coAllow = false;
    }
  }

  public void rotateAimCL(boolean slow) {
    //Rotates the aimer clockwise
      if (slow){
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SLOW_SPEED); 
      } else {
        aimMotor.set(ControlMode.PercentOutput, -Constants.AIM_SPEED);
      }
  }

  public void rotateAimCO(boolean slow){
      //Rotates the aimer counter clockwise
      if(slow){
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SLOW_SPEED);
      } else {
        aimMotor.set(ControlMode.PercentOutput, Constants.AIM_SPEED);
      }
  }

  public void stopAim(){
    //Stops the aimer motor
    aimMotor.set(ControlMode.PercentOutput, Constants.AIM_STOP);
  }
}