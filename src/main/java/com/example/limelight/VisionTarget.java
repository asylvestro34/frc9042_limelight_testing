package com.example.limelight;

public record VisionTarget(
        int id,
        double x,
        double y,
        double z,
        double latencyMs
) {}
