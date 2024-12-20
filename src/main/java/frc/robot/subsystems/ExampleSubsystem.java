// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.Command;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;

public class ExampleSubsystem extends SubsystemBase {
  private VictorSPX motor = new VictorSPX(41);
  private DigitalInput limit = new DigitalInput(2);
  private boolean inverted = false;
  private double speed = 0.0;






  /** Creates a new ExampleSubsystem. */
  public ExampleSubsystem() {}

  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(() -> {
      System.out.println("Running example method.");
      // for (int i = 0; i < 10; i++) {
      //   System.out.println("we init sigmas");
      // }
    });
  }

  public Command speedUp() { 
    return runOnce(() -> speed += 0.05); 
  }

  public Command slowDown() { 
    return runOnce(() -> speed -= 0.05); 
  }

  public Command setSpeed(double s) { 
    return runOnce(() -> speed = s); 
  }

  public Command invertMotor() {
    return runOnce(() -> {
      inverted = !inverted;
      motor.setInverted(inverted);
    });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  public double getEncoderValue() {
    return 0; //motor.getEncoder().getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("Speed", speed);
    //SmartDashboard.putNumber("Encoder Absolute", this.getEncoderValue());
    SmartDashboard.putBoolean("Limit", limit.get());
    motor.set(VictorSPXControlMode.PercentOutput, speed);

    if (limit.get() == false){
      motor.set(VictorSPXControlMode.PercentOutput, 0);
    }

    else{
      motor.set(VictorSPXControlMode.PercentOutput, speed);
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public boolean limitInfo(){
    return limit.get();
  }

}
