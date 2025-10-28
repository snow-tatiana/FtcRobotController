package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class IfPractice extends OpMode {

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        //boolean aButton = gamepad1.a; //press TRUE, depress FALSE
        /*
        if (aButton) {
            telemetry.addData("A button", "Pressed!");
        }
        else {
            telemetry.addData("A button", "NOT Pressed");

        }
        telemetry.addData("A button state", aButton);

        */

        /*
        double speedForward = -gamepad1.left_stick_y;
        double leftY = speedForward;

        if (leftY < 0){
            telemetry.addData("Left Stick", "is Negative");
        }
        else if (leftY > 0.5){
            telemetry.addData("Left Stick", "is greater than 50%")
        }
        else if (leftY > 0) {
            telemetry.addData("Left Stick", "is greater than 0");
        }
        else {
            telemetry.addData("Left Stick", "is Zero");
        }
        telemetry.addData("Left Stick", leftY);

         */

        double speedForward = -gamepad1.left_stick_y;
        double leftY = speedForward;

        if(leftY < 0.1 && leftY > -0.1){
            telemetry.addData("Left Stick", "In Dead Zone");
        }

        telemetry.addData("Left Stick", leftY);

        double motorSpeed = gamepad1.left_stick_y;
        boolean aButton = gamepad1.a;

        if (!aButton) {
            motorSpeed *= 0.5;
        }
        else {
            motorSpeed = motorSpeed;
        }

    }
}

/*
AND - && if (LeftY < 0.5 && Left Y > 0) {
OR - || if (leftY < 0 || Left Y < 0) {
NOT - ! if (!clawClosed) {
 */