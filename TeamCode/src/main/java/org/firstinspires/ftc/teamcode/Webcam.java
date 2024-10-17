package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class Webcam extends OpMode {

    OpenCvWebcam webcam1 = null;

    @Override
    public void init() {

        WebcamName webcamName = hardwareMap.get(WebcamName.class,"webcam1");
int cameraMoniterViewID = hardwareMap.appContext.getResources().getIdentifier( "cameraMonitorViewId",  "id",hardwareMap.appContext.getPackageName());
 webcam1 = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMoniterViewID);
}
    @Override
    public void loop() {

    }
}