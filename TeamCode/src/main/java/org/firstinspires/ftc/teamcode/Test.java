package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@Autonomous
public class Test extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Hello", "Tatiana");
    }

    @Override
    public void loop() {

        // single line comment

    }
}
