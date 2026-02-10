package com.example.limelight;

import java.util.List;

public class VisionConsumer {

    private final VisionSource source;

    public VisionConsumer(VisionSource source) {
        this.source = source;
    }

    public void update() {
        List<VisionTarget> targets = source.getTargets();

        if (targets.isEmpty()) {
            System.out.println("No AprilTags detected");
            return;
        }

        for (VisionTarget t : targets) {
            System.out.printf(
                "Tag %d | X=%.2f Y=%.2f Z=%.2f | Latency=%.1fms%n",
                t.id(), t.x(), t.y(), t.z(), t.latencyMs()
            );
        }
    }
}
