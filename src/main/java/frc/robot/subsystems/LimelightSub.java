// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*
  @Vic
  @New Hawks
*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;

public class LimelightSub extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public static boolean toRight = false;
  public static boolean targetSeen;
  /** Creates a new LimelightSub. */
  public LimelightSub() {

  }

  public void runLimeNum() {
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

    System.out.println(tarX);
    SmartDashboard.putBoolean("TAR SEE", targetSeen);
    if(tarX > 5){
      //Turn to the right
      toRight = false;
      SmartDashboard.putBoolean("ToRight", false);

    } else if (tarX < -5) {
      // Turn to the left
      toRight = true;
      SmartDashboard.putBoolean("ToRight", true);

    } else {

    }

    if(tarX == 0 && tarY == 0 || (tarX < 3 && tarX > -3)){ //2 is nice
      targetSeen = false;
    } else {
      targetSeen = true;
    }


  }

  public void recall() {
    //Set current degree of shooter turn to 0
  }

  public void getDist(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngleVertical = ty.getDouble(0.0);

    double angleToGoalDegrees = Constants.LIME_DEGREES + targetOffsetAngleVertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distInch = (Constants.GOAL_HEIGHT - Constants.LIME_LENS_HEIGHT)/Math.tan(angleToGoalRadians);

    //Feet
    int distFeet = ((int)distInch / 12);
    int remInch = ((int)distInch % 12);

    String distOut = String.valueOf(distFeet) + "'" + String.valueOf(remInch);

    SmartDashboard.putString("Distance to Target", distOut);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}