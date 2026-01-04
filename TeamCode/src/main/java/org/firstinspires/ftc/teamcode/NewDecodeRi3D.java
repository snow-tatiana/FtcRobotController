package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class NewDecodeRi3D extends OpMode {
    MecanumDrive drive = new MecanumDrive();

    private DcMotorEx leftLauncher = null;
    private DcMotorEx rightLauncher = null;
    private DcMotorEx intake = null;
    private CRServo leftFeeder = null;
    private CRServo rightFeeder = null;
    private Servo diverter = null;
    boolean intakePressed = false;
    boolean launchersPressed = false;
    boolean feedersPressed = false;
    double servoPosition = 0.5;


    @Override
    public void init() {
        drive.init(hardwareMap);


        leftLauncher = hardwareMap.get(DcMotorEx.class, "left_launcher");
        rightLauncher = hardwareMap.get(DcMotorEx.class, "right_launcher");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        leftFeeder = hardwareMap.get(CRServo.class, "left_feeder");
        rightFeeder = hardwareMap.get(CRServo.class, "right_feeder");
        diverter = hardwareMap.get(Servo.class, "diverter");

        leftFeeder.setDirection(CRServo.Direction.FORWARD);
        rightFeeder.setDirection(CRServo.Direction.FORWARD);
        leftLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        rightLauncher.setDirection(DcMotorSimple.Direction.FORWARD);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);


        leftLauncher.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightLauncher.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        diverter.setPosition(servoPosition);
    }

    @Override
    public void loop() {

        if (intakePressed == false && gamepad1.aWasPressed()) {
            intakePressed = true;
            intake.setVelocity(2683);
        } else if (intakePressed == true && gamepad1.aWasPressed()) {
            intakePressed = false;
            intake.setPower(0);

        }

        if (launchersPressed == false && gamepad1.yWasPressed()) {
            launchersPressed = true;
            leftLauncher.setVelocity(560);
            rightLauncher.setVelocity(560);
        } else if (launchersPressed == true && gamepad1.yWasPressed()) {
            launchersPressed = false;
            leftLauncher.setVelocity(0);
            rightLauncher.setVelocity(0);

        }

        if (feedersPressed == false && gamepad1.xWasPressed()) {
            feedersPressed = true;
            leftFeeder.setPower(1);
            rightFeeder.setPower(1);
        } else if (feedersPressed == true && gamepad1.xWasPressed()) {
            feedersPressed = false;
            leftFeeder.setPower(0);
            rightFeeder.setPower(0);

        }

        if (gamepad1.dpadLeftWasPressed()) {
            servoPosition -= 0.05;
            if (servoPosition < 0.0) {
                servoPosition = 0.0;
            }
            diverter.setPosition(servoPosition);
        } else if (gamepad1.dpadRightWasPressed()) {
            servoPosition += 0.05;
            if (servoPosition > 1.0) {
                servoPosition = 1.0;
            }
            diverter.setPosition(servoPosition);
        }

        telemetry.addData("Servo position: ", servoPosition);
        telemetry.update();


        double forward = gamepad1.left_stick_x;
        double right = gamepad1.left_stick_y;
        double rotate = gamepad1.right_stick_x;

        drive.driveDriveFieldRelative(forward, right, rotate);
    }

}
