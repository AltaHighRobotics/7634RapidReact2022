// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.DriveTrainSub;
import frc.robot.subsystems.AimSub;

public class LimelightSub extends SubsystemBase {
  private DriveTrainSub m_driveTrainSub;
  private AimSub m_aimSub;

  int c = 0;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public boolean toRight = false;
  /** Creates a new LimelightSub. */
  public LimelightSub() {

  }

  public void runLimeNum() {
    c++;
    SmartDashboard.putNumber("NUMBER COUNTER PLESSE WORKKK", c);

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically; tar = target
    double tarX = tx.getDouble(0.0);
    double tarY = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tarX);
    SmartDashboard.putNumber("LimelightY", tarY);
    SmartDashboard.putNumber("LimelightArea", area);

    if(tarX > 10){
      //Turn to the right
      m_aimSub.rotateAim(true);
     toRight = true;
    } else if (tarX < -10) {
      // Turn to the left
      m_aimSub.rotateAim(false);
      toRight = false; 
    }

    SmartDashboard.putBoolean("TO THE RIGHT :: ", toRight);
  }

  public void recall() {
    //Set current degree of shooter turn to 0
  }

  public void getDist(){
    
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}