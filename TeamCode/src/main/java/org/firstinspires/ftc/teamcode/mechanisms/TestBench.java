package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestBench {

    private DigitalChannel touchSensor; // may give more descriptive name if need be
    private DcMotor motor;
    private double ticksPerRev; // revolution

    public void init(HardwareMap hwMap) {
        // touch sensor code
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);


        // motor speed code
        motor = hwMap.get(DcMotor.class, "motor"); // deviceName needs to be name in configuration
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();

    }
    public void setMotorSpeed(double speed) {
        // accepts values from -1.0 - 1.0
        motor.setPower(speed);

    }

    // touch sensor true/false states
    public boolean isTouchSensorPressed(){
        return !touchSensor.getState();
    }

    public boolean isTouchSensorReleased(){
        return touchSensor.getState();
    }

    // number of ticks counted by encoder per rev
    public double getMotorRevs() {
        return motor.getCurrentPosition() / ticksPerRev; // normalizing ticks to revolutions
    }
}


