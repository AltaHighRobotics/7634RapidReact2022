// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants { 
    // Motors.
    public static final int RIGHT_DRIVE_1 = 1;
    public static final int RIGHT_DRIVE_2 = 2;
    public static final int LEFT_DRIVE_1 = 3;
    public static final int LEFT_DRIVE_2 = 4;
    public static final int INTAKE_MOTOR = 5;
    public static final int FEEDER_MOTOR = 6;

    public static final int SHOOTER_MOTOR = 7;

    public static final int AIM_MOTOR = 9;
    public static final int WINCH_MOTOR = 8;
    public static final int VELCRO_MOTOR = 0;//Change later.

    // Sadness ):
    public static final double FEEDER_INVERT = 1; // 1 or -1

    // Piston.
    public static final int LIFT_PISTON = 15;//15;
    public static final int LIFT_PISTON_1 = 0;//Change later.
    public static final int LIFT_PISTON_2 = 0;//Change later.

    //Encoders.
    public static final int ARDUINO_SERIAL_SPEED = 9600;
    public static final int R_DRIVE_ENCODER_A = 0; // Encoders use 2 ports.
    public static final int R_DRIVE_ENCODER_B = 1;
    public static final int L_DRIVE_ENCODER_A = 2;
    public static final int L_DRIVE_ENCODER_B = 3;
    public static final int ARDUINO_MSG_LEN = 15;
    public static final double UNITS_PER_ROTATION = 2048.0;

    // Wheels, gears, and encoders.
    public static final double DRIVE_ENCODER_PULSES_PER_ROTION = 2048.0;
    public static final double DRIVE_ENCODER_DIS_PER_PULSE = 0.009203884727313847;

    // Speed.
    public static final double DRIVE_SPEED = 1.0; //0.6
    public static final double INTAKE_SPEED = 1.0;
    public static final double INTAKE_REV_SPEED = 0.5;
    public static final double FEEDER_SPEED = 0.8;
    public static final double FEEDER_REV_SPEED = 0.5;
    public static final double WINCH_SPEED = 1.0;//Just a place holder and test later.
    public static final double VELCRO_SPEED = 1.0; //Just a place holder and test later.

    public static final double STOP_SHOOTER = 0;
    public static final double START_SHOOTER = 0.79;
    public static final double MIN_SPEED_STICK = 0.3;

    public static final double SHOOTER_VELOCITY = 16650.0;

    public static final double AIM_SPEED = 0.2; //.4
    public static final double AIM_SLOW_SPEED = 0.14;
    public static final double AIM_SNAIL_SPEED = 0.11;
    public static final double AIM_ELLA_SPEED = 0.05;
    public static final double AUT_AIM_SPEED = 2;
    public static final double AIM_STOP = 0;
    public static final double MIN_AIM_SPEED = 0.04;
    
    public static final double AUTO_TURN_SPEED = 0.3;
    public static final double AUTO_DRIVE_SPEED = 0.25;

    public static final double MULTIPLIER = 0;
    public static final double SHOT_AIM_MULT = 0.2;

    // Here is some constants vic. Use them well.
    public static final int AIM_ROTATE_MAIN_SPEED = 0;
    public static final int AIM_ROTATE_SLOW_SPEED = 1;
    public static final int AIM_ROTATE_SNAIL_SPEED = 2;
    public static final int AIM_ROTATE_ELLA_SPEED = 3;

    // Limits.
    public static final double MAX_DRIVE_ACCE = 0.1;

    public static final int MAX_AIM_CL = -14293;
    public static final int MAX_AIM_CO = 12000;

    public static final double SHOOTER_MIN_VOLT = 1.0;
    public static final double SHOOTER_MAX_VOLT = 12.0;

    public static final double AIM_THRESH = 20.0; //25
    public static final double AIM_PRECISION = 1.0;
    public static final double TARGET_PRECISION = 3.0;

    // Autonmous.
    public static final double CLOCK_WISE = 1;
    public static final double COUNTER_CLOCK_WISE = -1;
    public static final double FORWARD = 1.0;
    public static final double BACKWARD = -1.0;
    public static final double TURN_MIN = 5.0;
    public static final double DRIVE_MIN = 5.0;
    public static final double AUTO_TURN_SLOWDOWN_DIS = 20.0;
    public static final double AUTO_DRIVE_SLOW_AT = 10.0;
    public static final double AUTO_MIN_DRIVE = 0.15;
    public static final double AUTO_DISTANCE1 = 52.523;
    public static final double AUTO_TURN_DISTANCE = 200.0;
    public static final double AUTO_DISTANCE_SHOOT1 = 32.523;
    public static final int AUTO_SHOOT_TIME = 100;

    // Shooter pid.
    public static final double SHOOTER_PORPORTIONAL_GAIN = 0.000006;
    public static final double SHOOTER_INTERGRAL_GAIN = 0.000003;
    public static final double SHOOTER_DERIVITIVE_GAIN = 0;
    public static final double SHOOTER_MAX_PROPORTIONAL = 1;
    public static final double SHOOTER_MAX_INTEGRAL = 1;
    public static final double SHOOTER_MAX_DERIVITIVE = 0;
    public static final double SHOOTER_POWER_OFFSET = 0.1;

    // Navx.
    public static final byte NAVX_UPDATE_RATE = (byte)100; // 4 to 200.

    // Turning.
    public static final double TURN_RAD = 0.4;
    public static final double LOW_TURN_THRESHHOLD = 0.3;
    public static final double LOW_TURN_MULTIPLIER = 2.0; // Don't make this value 0!
    public static final double AUTO_TURN_CORRECT = 0.02;

    //Limelight
    public static final double LIME_DEGREES = 34.0;
    public static final double LIME_LENS_HEIGHT = 37.0; //in inches
    public static final double GOAL_HEIGHT = 104.0; //in inches
    public static final double TARGET_DIST = 87.3; //inches
    public static final double LIMELIGHT_KP = 0.012;
    public static final double LIMELIGHT_KD = 0.0;
    public static final double LIMELIGHT_SLOW_DOWN_DIS = 10.0;
    public static final double LIMELIGHT_MIN_TURN = 2.0;
    public static final double LIMELIGHT_TURN_SPEED = 0.15;
    public static final double LIMELIGHT_TURN_SPEED_2 = 0.095;
    public static final double TARGET_HEIGHT = 104.0;

    // Controller.
    public static final int DRIVER_CONTROLLER = 0;
    public static final int SHOTGUN_CONTROLLER = 1;

    public static final int RIGHT_STICK_Y = 3; //5 for flight, 3 for cntroller
    public static final int RIGHT_STICK_X = 4;
    public static final int LEFT_STICK_Y = 1;
    public static final int LEFT_STICK_X = 0;
    public static final int STICK_Z = 2;

    public static final int LEFT_TRIGGER = 2;
    public static final int RIGHT_TRIGGER = 3;

    public static final int XBOX_A_BUTTON = 1; 
    public static final int XBOX_B_BUTTON = 2;
    public static final int XBOX_X_BUTTON = 3;
    public static final int XBOX_Y_BUTTON = 4;

    public static final int FLIGHT_BUTTON_5 = 5;
    public static final int FLIGHT_BUTTON_6 = 6;
    public static final int FLIGHT_BUTTON_7 = 7;
    public static final int FLIGHT_BUTTON_8 = 8;
    public static final int FLIGHT_BUTTON_9 = 9;
    public static final int FLIGHT_BUTTON_10 = 10;
    public static final int FLIGHT_BUTTON_11 = 11;
    public static final int FLIGHT_BUTTON_12 = 12;

    public static final int LIMELIGHT_ON = 3;
    public static final int LIMELIGHT_OFF = 1;
}
