package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
@TeleOp(name="Force Quit Main", group="Robot")
public class ForceQuitMain extends LinearOpMode {
    // Defines the motors for use
    private DcMotor frontLeft, backLeft, backRight, frontRight, flywheel1, flywheel2;
    private double aimLastUpdate = 0.0;
    private int aimStep = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Configurations on the Driver Hub Config name(s)
        // The names in "" should be exactly the same in the Driver Hub
        frontLeft  =  hardwareMap.get(DcMotor.class, "fl");
        frontRight =  hardwareMap.get(DcMotor.class, "fr");
        backLeft   =  hardwareMap.get(DcMotor.class, "bl");
        backRight  =  hardwareMap.get(DcMotor.class, "br");
        flywheel1  =  hardwareMap.get(DcMotor.class, "fw1");
        flywheel2  =  hardwareMap.get(DcMotor.class, "fw2");

        // Brake the drive motors when power = 0 to reduce coasting
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Runs the motors with incoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Waits until the start button is pressed then continues
        waitForStart();
        aimLastUpdate = getRuntime();
        double fireStartTime = -1;

        //After start button is pressed this happens
        while (opModeIsActive()) {
            // Controls for driving
            double forward = -gamepad1.left_stick_y; // push forward -> positive
            double strafe  = gamepad1.right_stick_x; // right stick X used for strafing
            double turn    = gamepad1.left_stick_x;  // left stick X used for turning

            /*
            Controls for how the driving works, forward, strafe, and turn
            are variables that control the way you can move the robot. Adding
            the + or - signs flips the direction the motors move.
             */
            double fl = Range.clip((forward - strafe + turn), 0, 1);
            double fr = Range.clip((-forward - strafe - turn), 0, 1);
            double bl = Range.clip((-forward - strafe + turn), 0, 1);
            double br = Range.clip((forward - strafe - turn), 0, 1);

            /*
            Additional controls for how the driving works. These should always match the
            variables above.
             */
            frontLeft.setPower(Range.clip((forward + strafe + turn), -1, 1));
            frontRight.setPower(Range.clip((-forward + strafe + turn), -1, 1));
            backLeft.setPower(Range.clip((-forward + strafe - turn), -1, 1));
            backRight.setPower(Range.clip((forward + strafe - turn), -1, 1));

            /*
             Example code for how to make a motor spin using the gamepad

              if(gamepad1.BUTTON) {
                flywheel1.setPower(1);
                flywheel2.setPower(1);
              }

             Else is VERY important so the motors stop when the button is released

              else {
                flywheel1.setPower(0);
                flywheel2.setPower(0);
              }
             */
            if(gamepad1.right_bumper) {
                flywheel1.setPower(-1);
                flywheel2.setPower(-1);
            }
            else {
                flywheel1.setPower(0);
                flywheel2.setPower(0);
            }



        }
    }
}