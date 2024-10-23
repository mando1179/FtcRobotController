package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class Webcam extends OpMode {

    OpenCvWebcam webcam1 = null;

    @Override
    public void init() {

        WebcamName webcamName = hardwareMap.get(WebcamName.class,"webcam1");
int cameraMoniterViewID = hardwareMap.appContext.getResources().getIdentifier( "cameraMonitorViewId",  "id",hardwareMap.appContext.getPackageName());
 webcam1 = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMoniterViewID);

 webcam1.setPipeline(new examplePipeline());

 webcam1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
     @Override
     public void onOpened() {
webcam1.startStreaming(640,360, OpenCvCameraRotation.UPRIGHT);
     }

     @Override
     public void onError(int errorCode) {

     }
 });
}
    @Override
    public void loop() {

    }
    class examplePipeline extends OpenCvPipeline {
        Mat YCbCr = new Mat();
        Mat leftcrop;
        Mat rightcrop;
        double leftavgfin;
        double rightavgfin;
        Mat outPut = new Mat();
        Scalar rectColor = new Scalar(255.0, 0.0, 0.0);

        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, YCbCr, Imgproc.Color_RGB2YCrCb);
            telemetry.addline("pipeline running");

            Rect leftRect = new Rect(x:1, y:1, width:319, height:359);
            Rect rightRect = new Rect(x:320, y:1, width:319, height 359);

            input.copyTo(outPut);
            Imgproc.rectangle(outPut, leftRect, rectColor, thickness:2);
            Imgproc.rectangle(outPut, rightRect, rectColor, thickness:2);

            leftCrop = YCbCr.submat(leftRect);
            rightCrop = YCbCr.submat(rightRect);

            Core.extractChannel(leftCrop, leftCrop, coi:2);
            Core.extractChannel(rightCrop, rightcrop, coi:2);

            Scalar leftavg = Core.mean(leftCrop);
            Scalar rightavg = Core.mean(rightCrop);

            leftavgfin = leftavg.val[0];
            rightavgfin = rightavg.val[0];

            if (leftavgfin > rightavgfin) {
                telemetry.addLine(s:"Left");
            } else {
                telementry.addLine(s:"Right");

            }
            return (outPut);
        }
    }
}


