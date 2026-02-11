# LimelightAprilTags

This is a simple Java project to read AprilTags detected by a Limelight 3G camera and print their data to the console. Designed for testing on a laptop but can be used as a starting point for FRC robot integration.

## Project Structure:
```
LimelightAprilTags/
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── limelight/
                        └── LimelightReader.java
                        testing
```

## Prerequisites:

- Java 17 or newer

- Maven 3.x
- - testing

- Limelight 3G camera on the same network as your computer

- (Optional) IDE like IntelliJ IDEA or VSCode

## Setup:

1. Clone or copy this project. and test

2. Make sure your pom.xml has all dependencies for NetworkTables (required for Limelight 3G communication).

3. Ensure your computer can reach the Limelight’s IP (default: http://10.xx.yy.11:5800/json
) — replace with your camera’s IP if different.

## Running the Project:

From the project root:

```
mvn clean compile exec:java -Dexec.mainClass=com.example.limelight.LimelightReader
```

Expected output:

- "Limelight now reachable" → Successfully connected to the camera

- "No AprilTags detected" → Camera reachable, no tag in view

- "AprilTag detected!" → Tag ID, family, and positional data printed

Example Output:
```
Limelight now reachable
Detected 2 AprilTag(s)
Finished processing AprilTags
Tag 586 | X=-0.50 Y=-0.20 Z=1.45 | Latency=43.1ms
Tag 585 | X=-0.22 Y=-0.22 Z=1.54 | Latency=43.1ms
```

## Notes:

- Ensure the camera is set to detect AprilTags and has the correct pipeline active.

- The JSON endpoint is used for reading tag data (http://<limelight-ip>/json).

- This project is designed for testing on a laptop; you can later copy the LimelightReader.java class into your robot project.

## Next Steps:

- Integrate with a robot program using WPILib and NetworkTables.

- Use the tag position (tx, ty, ta) for autonomous navigation or field localization.