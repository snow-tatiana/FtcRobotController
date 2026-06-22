package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareComponents {

    public DcMotor frontLeft, backLeft, backRight, frontRight, kicker, flywheel1, flywheel2;
    public Servo gate;

    public void init(HardwareMap hwMap) {

        //drivetrain hwMaps - DcMotor
        frontLeft = hwMap.get(DcMotor.class, "front_left");
        frontRight = hwMap.get(DcMotor.class, "front_right");
        backLeft = hwMap.get(DcMotor.class, "back_left");
        backRight = hwMap.get(DcMotor.class, "back_right");

        //flywheel hwMaps - DcMotor
        flywheel1 = hwMap.get(DcMotor.class, "fly_wheel_right");
        flywheel2 = hwMap.get(DcMotor.class, "fly_wheel_left");

        // kicker hwMap - DcMotor
        kicker = hwMap.get(DcMotor.class, "kicker");

        //gate hwMap - servo
        gate = hwMap.get(Servo.class, "gate");


        //Reverses direction for standard drive orientation
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);


        // Encoder setup
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Set braking behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        kicker.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        //set all motors to zero power
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        flywheel1.setPower(0);
        flywheel2.setPower(0);
    }


}
