package com.example.limelight;

public class LimelightReader {

    public static void main(String[] args) throws Exception {

        VisionSource source = new HttpVisionSource();
        VisionConsumer consumer = new VisionConsumer(source);

        System.out.println("Limelight AprilTag Reader Started");

        // Main loop: update every 2 seconds
        while (true) {
            consumer.update();
            Thread.sleep(2000);
        }
    }
}
