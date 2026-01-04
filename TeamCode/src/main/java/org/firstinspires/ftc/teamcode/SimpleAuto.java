package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class SimpleAuto extends LinearOpMode {

    /*
    final double TARGET_POSITION = 100;
    final double POWER = 0.25;
    */

    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotorEx leftLauncher = null;
    private DcMotorEx rightLauncher = null;
    private DcMotorEx intake = null;
    private CRServo leftFeeder = null;
    private CRServo rightFeeder = null;
    private Servo diverter = null;
    private ElapsedTime runtime = new ElapsedTime();
    boolean shootTimerStarted = false;

    public enum AutoState {
        DRIVE_BACKWARD,
        SHOOT,
        STOP
    }

    AutoState currentState = AutoState.DRIVE_BACKWARD;


    @Override
    public void runOpMode() {

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        leftLauncher = hardwareMap.get(DcMotorEx.class, "left_launcher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "right_launcher");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftFeeder = hardwareMap.get(CRServo.class, "left_feeder");
        rightFeeder = hardwareMap.get(CRServo.class, "right_feeder");
        diverter = hardwareMap.get(Servo.class, "diverter");

        //reverses the direction of the intake
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        //corrects the drivetrain to drive straight instead of rotating
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        //spins the feeders in the correct direction
        leftFeeder.setDirection(CRServo.Direction.FORWARD);
        rightFeeder.setDirection(CRServo.Direction.FORWARD);

        //spins the launchers in the correct direction
        leftLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLauncher.setDirection(DcMotorSimple.Direction.FORWARD);

        //stops the drivetrain motors from spinning exactly when they are stopped
        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //brakes the launchers when they are set to zero
        leftLauncher.setZeroPowerBehavior((DcMotor.ZeroPowerBehavior.BRAKE));
        rightLauncher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //ensures the encoders are reset to zero to zero when starting
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets the target distance to 100 encoder ticks backwards
        leftFrontDrive.setTargetPosition(-1000);
        rightFrontDrive.setTargetPosition(-1000);
        leftBackDrive.setTargetPosition(-1000);
        rightBackDrive.setTargetPosition(-1000);

        //sets the drivetrain motors to run to the set encoder distance
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //sets the intake and both side launchers to run without encoders
        //this is because they run for a set amount of time
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftLauncher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        //this is the state machine
        //it moves the robot backwards, shoots for a set amount of time, then ensures all motors
        //and servos are stopped
        while (opModeIsActive()){
            switch (currentState) {
                //moves the robot backwards to the target position at a set power
                case DRIVE_BACKWARD:
                    leftFrontDrive.setPower(0.25);
                    rightFrontDrive.setPower(0.25);
                    leftBackDrive.setPower(0.25);
                    rightBackDrive.setPower(0.25);

                    if (!leftFrontDrive.isBusy()
                            && !rightFrontDrive.isBusy()
                            && !leftBackDrive.isBusy()
                            && !rightBackDrive.isBusy()){

                        leftFrontDrive.setPower(0);
                        rightFrontDrive.setPower(0);
                        leftBackDrive.setPower(0);
                        rightBackDrive.setPower(0);

                        currentState = AutoState.SHOOT;
                    }
                    break;

                //runs the shooter mechanism for a set amount of time at a set power, then stops it
                case SHOOT:
                    intake.setVelocity(2415);
                    leftFeeder.setPower(1);
                    rightFeeder.setPower(1);
                    leftLauncher.setVelocity(2100);
                    rightLauncher.setVelocity(2100);

                    if (!shootTimerStarted) {
                        runtime.reset();
                        shootTimerStarted = true;
                    }

                    if (runtime.seconds() >= 16.0) {

                        intake.setVelocity(0);
                        leftFeeder.setPower(0);
                        rightFeeder.setPower(0);
                        leftLauncher.setVelocity(0);
                        rightLauncher.setVelocity(0);

                        currentState = AutoState.STOP;
                        runtime.reset();
                    }
                    break;

                //stops all motors and servos
                case STOP:
                    leftFrontDrive.setPower(0);
                    rightFrontDrive.setPower(0);
                    leftBackDrive.setPower(0);
                    rightBackDrive.setPower(0);
                    intake.setVelocity(0);
                    leftFeeder.setPower(0);
                    rightFeeder.setPower(0);
                    leftLauncher.setVelocity(0);
                    rightLauncher.setVelocity(0);

                    break;

            }
        }

        telemetry.addData("Status", "Stopped");
        telemetry.update();

    }
}