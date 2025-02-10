// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Spark leftMotorFront = new Spark(0);
  private Spark leftMotorBack = new Spark(1);
  private Spark rightMotorFront = new Spark(2);
  private Spark rightMotorBack = new Spark(3);

  private Joystick joy1 = new Joystick(0);

  double startTime;
  double timeSinceStart;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("timeSinceStart", timeSinceStart);
    SmartDashboard.putNumber("Left Motor Front", leftMotorFront.get());
    SmartDashboard.putNumber("Left Motor Back", leftMotorBack.get());
    SmartDashboard.putNumber("Right Motor Front", rightMotorFront.get());
    SmartDashboard.putNumber("Right Motor Back", rightMotorBack.get());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    
    startTime = Timer.getFPGATimestamp();

    System.out.println("auto init");
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
    timeSinceStart = Timer.getFPGATimestamp() - startTime;
    
    //System.out.println("auto periodic running");
    
    
    switch (m_autoSelected) {
      case kCustomAuto:
    
        System.out.println("custom auto");
       
      break;
      case kDefaultAuto:
      default:
       
        System.out.println("default auto");
        if (timeSinceStart < 2 && 0 < timeSinceStart ) { //forwards I think
          leftMotorFront.set(0.5);
          leftMotorBack.set(0.5);
          rightMotorFront.set(-0.5);
          rightMotorBack.set(-0.5);
        }if (timeSinceStart  < 4 && 2 < timeSinceStart ) { //Backwards?????
          leftMotorFront.set(-0.2);
          leftMotorBack.set(-0.2);
          rightMotorFront.set(0.2);
          rightMotorBack.set(0.2);
        }if (timeSinceStart  < 6 && 4 < timeSinceStart ) { // spinnnnn
          leftMotorFront.set(0.2);
          leftMotorBack.set(0.2);
          rightMotorFront.set(0.2);
          rightMotorBack.set(0.2);
        }
        System.out.println("it did smth");
    
      break;
    } 


  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    System.out.println("Teleop");
    double speed = joy1.getRawAxis(1) * 0.6;
    double turn = -joy1.getRawAxis(4) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    leftMotorFront.set(left);
    leftMotorBack.set(left);
    rightMotorFront.set(-right);
    rightMotorBack.set(-right);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}