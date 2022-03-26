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

import java.lang.Math;

public class LimelightSub extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public static boolean toRight;
  public static boolean targetSeen;
  public static boolean goingCL;
  public static boolean auSlow;
  public static double absX;
  
  public static double tarX;
  public static double tarY;

  /** Creates a new LimelightSub. */
  public LimelightSub() {

  }

  public void runLimeNum() { //Determines if the aimer shold go left ro right
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically; tar = target
    tarX = tx.getDouble(0.0);
    tarY = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //gets abs value
    absX = Math.abs(tarX);
    
    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tarX);
    SmartDashboard.putNumber("LimelightY", tarY);
    SmartDashboard.putNumber("LimelightArea", area);

    SmartDashboard.putBoolean("TAR SEE", targetSeen);

    if(tarX > 0){
      //Turn to the right
      toRight = false;
      SmartDashboard.putBoolean("ToRight", false);
    } else if (tarX < 0) {
      // Turn to the left
      toRight = true;
      SmartDashboard.putBoolean("ToRight", true);
    }

    if(tarX == 0 && tarY == 0) { //3 is nice
      targetSeen = false;
    } else {
      targetSeen = true;
    }
  }

  public void recall() {}

  public void getDist(){ //Returns the distance of limelight target
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngleVertical = ty.getDouble(0.0);

    double angleToGoalDegrees = Constants.LIME_DEGREES + targetOffsetAngleVertical;
    double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

    //calculate distance
    double distInch = (Constants.GOAL_HEIGHT - Constants.LIME_LENS_HEIGHT) / Math.tan(angleToGoalRadians);

    //Feet
    int distFeet = ((int)distInch / 12);
    int remInch = ((int)distInch % 12);

    String distOut = String.valueOf(distFeet) + "'" + String.valueOf(remInch);

    SmartDashboard.putString("Distance to Target", distOut);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    runLimeNum(); 
  }
}