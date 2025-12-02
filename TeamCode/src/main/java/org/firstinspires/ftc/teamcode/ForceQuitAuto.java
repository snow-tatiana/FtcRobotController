package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanisms.HardwareComponents;

@Autonomous(name="Force Quit Auto", group = "Robot Auto")
public class ForceQuitAuto extends OpMode {
    private static final Object DRIVE_SPEED = ;
    HardwareComponents RobotHardware = new HardwareComponents();
    int state;

    @Override
    public void init() {
        RobotHardware.init(hardwareMap);

        telemetry.addData("Status", "Resetting Encoders");

        //resets encoders
        RobotHardware.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotHardware.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotHardware.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotHardware.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotHardware.flywheel1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RobotHardware.flywheel2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //displays what the current encoder values are at
        telemetry.addData("frontLeft encoder value:", RobotHardware.frontLeft.getCurrentPosition());
        telemetry.addData("frontRight encoder value:", RobotHardware.frontRight.getCurrentPosition());
        telemetry.addData("backLeft encoder value:", RobotHardware.backLeft.getCurrentPosition());
        telemetry.addData("backRight encoder value:", RobotHardware.backRight.getCurrentPosition());
        telemetry.addData("flywheel1 encoder value:", RobotHardware.flywheel1.getCurrentPosition());
        telemetry.addData("flywheel2 encoder value:", RobotHardware.flywheel2.getCurrentPosition());

        if (RobotHardware.frontLeft.getCurrentPosition() == 0) {
            state = 0;
            return;
        }

    }

    @Override
    public void loop() {

        telemetry.addData("Our State", state);
        switch (state) {
            case 0:
                telemetry.addLine("State 0");
                //set robot to drive certain amount of distance based off of encoder cables
                if () {
                    state = 1;
                }
                break;
            case 2:
                telemetry.addLine("To exit state, press B");
                if (gamepad1.b) {
                    state = 3;
                }
                break;
            case 3:
                telemetry.addLine("To exit state, press X");
                if (gamepad1.x) {
                    state = 4;
                }
                break;
            default:
                telemetry.addLine("Auto State machine finished");
        }

    }
}
