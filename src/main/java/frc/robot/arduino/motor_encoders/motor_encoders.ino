/*
  @Hacker
  @New Hawks
*/

#include <Encoder.h>

#define GET_RIGHT 1
#define GET_LEFT 2

#define RESET_ALL 20
#define RESET_RIGHT 21
#define RESET_LEFT 22

#define UNITS_PER_ROTION 2048
#define WHEEL_CIR 0.0
#define DRIVE_GEAR_RAD 0.0

#define MSG_LEN 15

Encoder rightDrive(0, 0);
Encoder leftDrive(0, 0);

double rightDis = 0.0;
double leftDis = 0.0;

void setup() {
  reset_encoders();
  Serial.begin(9600);
}

void loop() {
  rightDis = (double)rightDrive.read() / UNITS_PER_ROTION / DRIVE_GEAR_RAD * WHEEL_CIR;
  leftDis = (double)leftDrive.read() / UNITS_PER_ROTION / DRIVE_GEAR_RAD * WHEEL_CIR;
  
  handle_serial();
  delay(50);
}

void handle_serial() {
  char outputStr[MSG_LEN];
  
  if (!Serial.availableForWrite() || !Serial.available())
    return;

  // Send info.
  switch (Serial.read()) {
    case GET_RIGHT:
      snprintf(outputStr, MSG_LEN, "%lf", rightDis);
      Serial.print(outputStr);
      break;
    case GET_LEFT:
      snprintf(outputStr, MSG_LEN, "%lf", leftDis);
      Serial.print(outputStr);
      break;
    case RESET_ALL:
      reset_encoders();
      break;
    case RESET_RIGHT:
      rightDrive.write(0);
      break;
    case RESET_LEFT:
      leftDrive.write(0);
      break;
    default:
      break;
  }
}

void reset_encoders() {
  rightDrive.write(0);
  leftDrive.write(0);
}
