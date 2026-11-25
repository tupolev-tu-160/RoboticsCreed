
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Drivetrain Test")
public class DrivetrainTest extends LinearOpMode {

    private DcMotor frontLeft, frontRight;
    private DcMotor backLeft, backRight;

    @Override
    public void runOpMode() {

        // Initialise motors
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            // Joystick inputs
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;

            // Precision mode
            double multiplier =
                gamepad1.left_trigger > 0.1 ? 0.3 : 1.0;

            // Mecanum drive calculations
            frontLeft.setPower(
                (drive + strafe + turn) * multiplier);

            frontRight.setPower(
                (drive - strafe - turn) * multiplier);

            backLeft.setPower(
                (drive - strafe + turn) * multiplier);

            backRight.setPower(
                (drive + strafe - turn) * multiplier);
        }
    }
}
