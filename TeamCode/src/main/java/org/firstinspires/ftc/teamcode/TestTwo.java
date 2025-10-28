package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestTwo extends OpMode {


    @Override
    public void init() {
        int teamNumber = 23014;
        double motorSpeed = 0.75;
        boolean clawClosed = true;
        String teamName = "Force Quit";
        int motorAngle = 90;

        telemetry.addData("TeamNumber", teamNumber);
        telemetry.addData("MotorSpeed", motorSpeed);
        telemetry.addData("ClawClosed", clawClosed);
        telemetry.addData("Name", teamName);
        telemetry.addData("MotorAngle", motorAngle);
    }

    @Override
    public void loop() {

    }
}
