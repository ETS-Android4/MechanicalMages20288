

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MecanumDrive", group = "TeleOp")

public class MecanumDrive extends OpMode {

    static final int MOTOR_TICK_COUNTS = 751;

    Servo bucket;
    Servo L;
    Servo H;
    DcMotor right_drive;
    DcMotor left_drive;
    DcMotor back_right_drive;
    DcMotor back_left_drive;
    DcMotor Intake;
    DcMotor Slide;
    DcMotor Caro;


    double left_drivePower;
    double right_drivePower;
    double back_right_drivePower;
    double back_left_drivePower;


    @Override
    public void init() {
        left_drive = hardwareMap.dcMotor.get("left_drive");
        right_drive = hardwareMap.dcMotor.get("right_drive");
        back_right_drive = hardwareMap.dcMotor.get("back_right_drive");
        back_left_drive = hardwareMap.dcMotor.get("back_left_drive");
        right_drive.setDirection(DcMotor.Direction.REVERSE);
        back_right_drive.setDirection(DcMotor.Direction.REVERSE);
        Intake = hardwareMap.dcMotor.get("Intake");

        bucket = hardwareMap.servo.get("bucket");
        Slide = hardwareMap.dcMotor.get("Slide");
        Caro = hardwareMap.dcMotor.get("Caro");

        Slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Slide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    @Override
    public void loop() {

        //strafe wall

        //left_drivePower = gamepad1.left_trigger*-0.95;
        //right_drivePower = gamepad1.left_trigger*-1;

        //Movement Controller
        right_drivePower = gamepad1.right_stick_y * -1;
        back_left_drivePower = gamepad1.right_stick_y * -1;
        left_drivePower = gamepad1.left_stick_y * -1;
        back_right_drivePower = gamepad1.left_stick_y * -1;

        left_drive.setPower(left_drivePower);
        right_drive.setPower(right_drivePower);
        back_left_drive.setPower(left_drivePower);
        back_right_drive.setPower(right_drivePower);

        //core drive
        boolean rightbumper = gamepad1.right_bumper;
        boolean leftbumper = gamepad1.left_bumper;
        //attachments
        boolean down = gamepad2.left_bumper;
        boolean up = gamepad2.right_bumper;

        float brake = gamepad2.right_trigger;


        //boolean rightBumper = gamepad2.right_bumper;
        if (rightbumper) {
            left_drive.setPower(1); // left drive is 0
            right_drive.setPower(-1); // right drive is 2
            back_left_drive.setPower(-1); // back left drive is 1
            back_right_drive.setPower(1); // back right drive is 3
        } else if (leftbumper) {
            left_drive.setPower(-1);
            right_drive.setPower(1);
            back_left_drive.setPower(1);
            back_right_drive.setPower(-1);
        }

        if (gamepad1.dpad_up) {
            left_drive.setPower(0.83);
            right_drive.setPower(1);
            back_left_drive.setPower(0.83);
            back_right_drive.setPower(1);
        } else if (gamepad1.dpad_down) {
            left_drive.setPower(-0.83);
            right_drive.setPower(-1);
            back_left_drive.setPower(-0.83);
            back_right_drive.setPower(-1);
        } else {
            left_drive.setPower(left_drivePower);
            right_drive.setPower(right_drivePower);
            back_left_drive.setPower(left_drivePower);
            back_right_drive.setPower(right_drivePower);
        }


            if (gamepad2.y) {
                Intake.setPower(-1);
            } else {
                Intake.setPower(0);
            }

            if (gamepad2.a) {
                Caro.setPower(-0.75); //3/4's speed (right carousal)
            } else if (gamepad2.b) {
                Caro.setPower(0.75); // (left carousal)
            } else {
                Caro.setPower(0);
            }

            telemetry.addData("Encoder value", Slide.getCurrentPosition());
            telemetry.update();


            if (up && Slide.getCurrentPosition() >= -1700) {
                //if (up) {
                telemetry.addData("Slide_up", Slide.getCurrentPosition());
                telemetry.update();
                Slide.setPower(-0.4);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bucket.setPosition(0.1);

            } else if (down && Slide.getCurrentPosition() <= 0) {
                //else if (down) {
                telemetry.addData("Slide_down", Slide.getCurrentPosition());
                telemetry.update();
                Slide.setPower(0.5);
                bucket.setPosition(.4);


            } else {
                Slide.setPower(0);
            }

            if (gamepad2.x) {
                bucket.setPosition(-.6);
            } else {
            }

        /*if (Slide.getCurrentPosition() < (-500)) {
            bucket.setPosition(.6);
        } */

            //if (down && Slide.getCurrentPosition()>=0)
        /*if (down) {
            Slide.setPower(-0.5);
            telemetry.addData("Slide_down", Slide.getCurrentPosition());
            telemetry.update();
            bucket.setPosition(.78);

        } else {
            Slide.setPower(0);
        }

        /*if (Slide.getCurrentPosition() > (-500)) {
            bucket.setPosition(.8);
        }*/


        }
       /*  else if (up) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bucket.setPosition(.5);
        }
        else if (down) {
            bucket.setPosition(.80);
        }    */

    }




