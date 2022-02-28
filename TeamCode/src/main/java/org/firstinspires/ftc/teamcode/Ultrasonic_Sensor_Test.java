package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "Ultrasonic_Sensor_Test", group = "TeleOp")
public class Ultrasonic_Sensor_Test extends LinearOpMode {
    // Define variables for our potentiometer and motor
    AnalogInput ultrasonic;

    // Define variable for the current voltage
    double currentVoltage;

    @Override
    public void runOpMode() {
        // Get the potentiometer and motor from hardwareMap
        ultrasonic = hardwareMap.get(AnalogInput.class, "ultrasonic");

        // Loop while the Op Mode is running
        waitForStart();
        while (opModeIsActive()) {
            // Update currentVoltage from the potentiometer
            currentVoltage = ultrasonic.getVoltage();

            float voltage_per_5mm = (float) 4.883; // mvolts per 5mm
            double distance = 5 * currentVoltage / voltage_per_5mm;// voltage_per_cm;

            telemetry.addData("Voltage Reading is", currentVoltage);
            telemetry.addData("Distance is", distance);

            telemetry.update();



/*
            // Turn the motor on or off based on the potentiometer’s position
            if (distance < 10) {
                telemetry.addData("Distance is", currentVoltage);
                telemetry.update();
            }
            else {

            }

            // Show the potentiometer’s voltage in telemetry
*/
        }
    }
}