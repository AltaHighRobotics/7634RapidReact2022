// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants;

public class EncodersSub extends SubsystemBase {
  private SerialPort arduino;

  // Constants for serial.
  public static final byte GET_RIGHT = 1;
  public static final byte GET_LEFT = 2;

  public static final byte RESET_ALL = 20;
  public static final byte RESET_RIGHT = 21;
  public static final byte RESET_LEFT = 22;

  public EncodersSub() {
    try {
      arduino = new SerialPort(Constants.ARDUINO_SERIAL_SPEED, SerialPort.Port.kUSB);
    } catch (Exception e) {
      System.out.println("Failed to connect to arduino");
    }
  }

  public long getEncoderRequest(byte requestType) {
    int bytesReceived = 0;

    // Sent request.
    arduino.write(new byte[] {requestType}, 1);

    bytesReceived = arduino.getBytesReceived();

    if (bytesReceived <= 0) {
      return -1;
    }

    return Long.valueOf(arduino.readString(Constants.ARDUINO_MSG_LEN));
  }

  public void sentEncoderRequest(byte requestType) {
    arduino.write(new byte[] {requestType}, 1);
  }

  public double rightDrive() {
    return (double)getEncoderRequest(GET_RIGHT);
  }

  public double leftDrive() {
    return (double)getEncoderRequest(GET_LEFT);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
